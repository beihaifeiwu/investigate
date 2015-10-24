package com.freetmp.investigate.io.nio;

/**
 * Created by liupin on 2015/10/24.
 */
public class TimeServer {

  public static void main(String[] args) {
    int port = 8080;
    if(args != null && args.length > 0){
      try {
        port = Integer.valueOf(args[0]);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
    new Thread(timeServer, "NIO-TimeServer").start();
  }
}
