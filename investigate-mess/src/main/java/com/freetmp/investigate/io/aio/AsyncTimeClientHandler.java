package com.freetmp.investigate.io.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liupin on 2015/10/24.
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

  private AsynchronousSocketChannel client;
  private String host;
  private int port;
  private CountDownLatch latch;

  public AsyncTimeClientHandler(String host, int port){
    this.host = host;
    this.port = port;
    try {
      client = AsynchronousSocketChannel.open();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void completed(Void result, AsyncTimeClientHandler attachment) {
    byte[] req = "QUERY TIME ORDER".getBytes();
    ByteBuffer buffer = ByteBuffer.allocate(req.length);
    buffer.put(req);
    buffer.flip();
    client.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
      @Override public void completed(Integer result, ByteBuffer attachment) {
        if(attachment.hasRemaining()){
          client.write(attachment, attachment, this);
        } else {
          ByteBuffer readBuffer = ByteBuffer.allocate(1024);
          client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override public void completed(Integer result, ByteBuffer attachment) {
              attachment.flip();
              byte[] bytes = new byte[attachment.remaining()];
              attachment.get(bytes);
              try {
                String body = new String(bytes, "UTF-8");
                System.out.println("Now is : " + body);
                latch.countDown();
              } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
              }
            }

            @Override public void failed(Throwable exc, ByteBuffer attachment) {
              try {
                client.close();
                latch.countDown();
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          });
        }
      }

      @Override public void failed(Throwable exc, ByteBuffer attachment) {
        try {
          client.close();
          latch.countDown();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
    exc.printStackTrace();
    try {
      client.close();
      latch.countDown();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void run() {
    latch = new CountDownLatch(1);
    client.connect(new InetSocketAddress(host, port), this, this);
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
