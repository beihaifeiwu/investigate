package com.freetmp.investigate.io.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liupin on 2015/10/24.
 */
public class AsyncTimeServerHandler implements Runnable {

  private int port;
  CountDownLatch latch;
  AsynchronousServerSocketChannel serverSocketChannel;

  public AsyncTimeServerHandler(int port){
    this.port = port;
    try {
      serverSocketChannel = AsynchronousServerSocketChannel.open();
      serverSocketChannel.bind(new InetSocketAddress(port));
      System.out.println("The time server is start in port : " + port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void run() {
    latch = new CountDownLatch(1);
    serverSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler>() {
      @Override public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        attachment.serverSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
      }

      @Override public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
      }
    });

    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private final AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
      this.channel = channel;
    }

    @Override public void completed(Integer result, ByteBuffer attachment) {
      attachment.flip();
      byte[] body = new byte[attachment.remaining()];
      attachment.get(body);
      try {
        String req = new String(body, "UTF-8");
        System.out.println("The time server received order : " + req);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ?
            new Date().toString() : "BAD ORDER";
        doWrite(currentTime);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    private void doWrite(String currentTime) {
      if(currentTime != null && currentTime.trim().length() > 0){
        byte[] bytes = currentTime.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
          @Override public void completed(Integer result, ByteBuffer attachment) {
            if(attachment.hasRemaining()){
              channel.write(byteBuffer, byteBuffer, this);
            }
          }

          @Override public void failed(Throwable exc, ByteBuffer attachment) {
            exc.printStackTrace();
            try {
              channel.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
      }
    }

    @Override public void failed(Throwable exc, ByteBuffer attachment) {
      try {
        channel.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
