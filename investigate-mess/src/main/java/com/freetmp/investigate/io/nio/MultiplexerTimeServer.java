package com.freetmp.investigate.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liupin on 2015/10/24.
 */
public class MultiplexerTimeServer implements Runnable {

  private Selector selector;

  private ServerSocketChannel serverSocketChannel;

  private volatile boolean stop;

  public MultiplexerTimeServer(int port){
    try {
      selector = Selector.open();
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("The time server is start in port: " + port);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void stop(){
    this.stop = true;
  }

  @Override public void run() {
    while (!stop){
      try {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectionKeys.iterator();
        SelectionKey key = null;
        while (it.hasNext()){
          key = it.next();
          it.remove();
          try {
            handleInput(key);
          } catch (IOException e) {
            if(key != null){
              key.cancel();
              if(key.channel() != null) key.channel().close();
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    if(selector != null){
      try {
        selector.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void handleInput(SelectionKey key) throws IOException {
    if(key.isValid()){

      if(key.isAcceptable()){
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
      }

      if(key.isReadable()){
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(buffer);
        if(readBytes > 0){
          buffer.flip();
          byte[] bytes = new byte[buffer.remaining()];
          buffer.get(bytes);
          String body = new String(bytes, "UTF-8");
          System.out.println("The time server receive order : " + body);
          String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
              new Date().toString() : "BAD ORDER";
          doWrite(sc, currentTime);
        } else if (readBytes < 0){
          // close the channel
          key.cancel();
          sc.close();
        } else {
          // read zero byte, ignore
        }
      }
    }
  }

  private void doWrite(SocketChannel sc, String currentTime) throws IOException {
    if(currentTime != null && currentTime.trim().length() > 0){
      byte[] bytes = currentTime.getBytes();
      ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
      buffer.put(bytes);
      buffer.flip();
      sc.write(buffer);
    }
  }
}
