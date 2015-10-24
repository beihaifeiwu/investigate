package com.freetmp.investigate.io.aio;

/**
 * Created by liupin on 2015/10/24.
 */
public class TimeClient {
  public static void main(String[] args) {
    int port = 8080;
    if(args != null && args.length >= 1){
      try {
        port = Integer.valueOf(args[0]);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }

    new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-TimeClient").start();
  }
}