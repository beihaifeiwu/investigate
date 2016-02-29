package com.freetmp.dolphin.dependency.manager.artifact

import org.eclipse.aether.graph.DependencyNode
import org.eclipse.aether.graph.DependencyVisitor
import org.eclipse.aether.util.artifact.ArtifactIdUtils.equalsBaseId
import org.eclipse.aether.util.artifact.ArtifactIdUtils.toVersionlessId
import org.eclipse.aether.util.graph.manager.DependencyManagerUtils.getPremanagedScope
import org.eclipse.aether.util.graph.manager.DependencyManagerUtils.getPremanagedVersion
import org.eclipse.aether.util.graph.transformer.ConflictResolver
import java.io.PrintStream

/**
 * Created by LiuPin on 2015/6/10.
 */
class ConsoleDependencyGraphDumper(val out: PrintStream = System.out) : DependencyVisitor {

  val childInfos = arrayListOf<ChildInfo>()

  override fun visitLeave(node: DependencyNode?): Boolean {
    if (childInfos.isNotEmpty()) childInfos.removeAt(childInfos.size - 1)
    if (childInfos.isNotEmpty()) childInfos[childInfos.size - 1].index++
    return true
  }

  override fun visitEnter(node: DependencyNode?): Boolean {
    out.println("${formatIndentation()}${formatNode(node!!)}")
    childInfos.add(ChildInfo(node.children.size))
    return true
  }

  fun formatIndentation(): String = buildString {
    childInfos.forEachIndexed { i, childInfo -> append(childInfo.formatIndentation(i == childInfos.size - 1)) }
  }

  fun formatNode(node: DependencyNode): String {
    val a = node.artifact
    val d = node.dependency
    return buildString {
      append(a)
      if ( d != null && d.scope.length > 0) {
        append(" [${d.scope}")
        if (d.isOptional) append(", optional")
        append("]")
      }
      var premanaged = getPremanagedVersion(node)
      if (premanaged != null && premanaged != a.baseVersion) append(" (version managed from $premanaged)")
      premanaged = getPremanagedScope(node)
      if (premanaged != null && premanaged != d.scope) append(" (scope managed from $premanaged)")

      val winner = node.data[ConflictResolver.NODE_DATA_WINNER] as DependencyNode?
      if (winner != null && !equalsBaseId(a, winner.artifact)) {
        val w = winner.artifact
        append(" (conflicts with ")
        if (toVersionlessId(a).equals(toVersionlessId(w))) append(w.version)
        else append(w)
        append(")")
      }
    }
  }
}

class ChildInfo(val count: Int) {
  var index: Int = 0

  fun formatIndentation(end: Boolean): String {
    val last = index + 1 >= count
    if (end)
      return if (last) "\\-" else "+-"
    else
      return if (last) "  " else "| "
  }
}