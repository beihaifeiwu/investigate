package com.freetmp.investigate.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liupin on 2015/10/24.
 */
public class TimeClientHandler implements Runnable {

  private String host;
  private int port;
  private Selector selector;
  private SocketChannel socketChannel;
  private volatile boolean stop;

  public TimeClientHandler(String host, int port){
    this.host = host == null ? "127.0.0.1" : host;
    this.port = port;

    try {
      selector = Selector.open();
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  @Override public void run() {
    try {
      doConnect();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    while(!stop){
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
          } catch (Exception e) {
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
      SocketChannel sc = (SocketChannel) key.channel();
      if(key.isConnectable()){
        if(sc.finishConnect()){
          sc.register(selector, SelectionKey.OP_READ);
          doWrite(sc);
        } else {
          System.exit(1);
        }
      }
      if(key.isReadable()){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(buffer);
        if(readBytes > 0){
          buffer.flip();
          byte[] bytes = new byte[buffer.remaining()];
          buffer.get(bytes);
          String body = new String(bytes, "UTF-8");
          System.out.println("Now is : " + body);
          this.stop = true;
        } else  if (readBytes < 0){
          // close channel
          key.cancel();
          sc.close();
        } else {
          // read zero bytes, ignore
        }
      }
    }
  }

  private void doConnect() throws IOException {
    if(socketChannel.connect(new InetSocketAddress(host, port))){
      socketChannel.register(selector, SelectionKey.OP_READ);
      doWrite(socketChannel);
    } else {
      socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
  }

  private void doWrite(SocketChannel socketChannel) throws IOException {
    byte[] req = "QUERY TIME ORDER".getBytes();
    ByteBuffer buffer = ByteBuffer.allocate(req.length);
    buffer.put(req);
    buffer.flip();
    socketChannel.write(buffer);
    if(!buffer.hasRemaining()){
      System.out.println("Send order to server succeed.");
    }
  }
}
