package com.freetmp.dolphin.dependency.manager.artifact

import org.eclipse.aether.transfer.*
import java.io.PrintStream
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by LiuPin on 2015/6/9.
 */
class ConsoleTransferListener(var out: PrintStream = System.out) : AbstractTransferListener() {

  val downloads = ConcurrentHashMap<TransferResource, Long>()
  var lastLength: Int = 0

  override fun transferInitiated(event: TransferEvent?) {
    val message = if (event!!.requestType == TransferEvent.RequestType.PUT) "Uploading" else "Downloading"
    out.println("$message: ${event.resource?.repositoryUrl}${event.resource?.resourceName}")
  }

  override fun transferProgressed(event: TransferEvent?) {
    val resource = event!!.resource
    downloads.put(resource, event.transferredBytes)
    val sb = buildString {
      for ((key, value) in downloads)
        append("${getStatus(key.contentLength, value)}  ")

      pad(lastLength - length)
      append('\r')
    }
    out.print(sb)
  }

  override fun transferStarted(event: TransferEvent?) {
    super.transferStarted(event)
  }

  override fun transferFailed(event: TransferEvent?) {
    transferCompleted(event!!)
    if ( event.exception is MetadataNotFoundException)
      event.exception.printStackTrace(out)
  }

  override fun transferCorrupted(event: TransferEvent?) {
    event?.exception?.printStackTrace()
  }

  override fun transferSucceeded(event: TransferEvent?) {
    transferCompleted(event!!)
    val resource = event.resource
    val contentLength = event.transferredBytes
    if (contentLength >= 0) {
      val type = if (event.requestType == TransferEvent.RequestType.PUT) "Uploaded" else "Downloaded"
      val len = if (contentLength >= 1024) "${toKB(contentLength)} KB" else "$contentLength B}"
      var throughput = ""
      val duration = System.currentTimeMillis() - resource.transferStartTime
      if (duration > 0) {
        val kbPerSec = ((contentLength - resource.resumeOffset) / 1024.0) / (duration / 1000.0)
        throughput = " at ${DecimalFormat("0.0", DecimalFormatSymbols(Locale.ENGLISH)).format(kbPerSec)} KB/sec"
      }
      out.println("$type: ${resource.repositoryUrl}${resource.resourceName} ($len$throughput)")
    }
  }

  fun transferCompleted(event: TransferEvent) {
    downloads.remove(event.resource)
    out.print(buildString {
      pad(lastLength)
      append('\r')
    })
  }

  fun StringBuilder.pad(spaces: Int): StringBuilder {
    var block = "                                        "
    var mutable = spaces
    while ( mutable > 0) {
      val n = Math.min(spaces, block.length)
      append(block, 0, n)
      mutable -= n
    }
    return this
  }

  fun getStatus(complete: Long, total: Long): String {
    when {
      total >= 1024 -> return "${toKB(complete)}/${toKB(total)} KB"
      total >= 0 -> return "$complete/$total B"
      complete >= 1024 -> return "${toKB(complete)} KB"
      else -> return "$complete B"
    }
  }

  fun toKB(bytes: Long): Long {
    return (bytes + 1023) / 1024
  }

}
