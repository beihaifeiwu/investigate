package com.freetmp.investigate.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

/**
 * websocket服务器
 * @author Pin Liu
 * @编写日期 2015年1月14日上午11:42:02
 */
public class WebSocketTyrusServer {
  
  public static void main(String...args){
    runTyrusServer();
  }
  
  /**
   * 使用Tyrus容器启动websocket服务
   * @author Pin Liu
   * @编写日期: 2015年1月14日下午1:15:01
   */
  public static void runTyrusServer(){
    Server server = new Server("localhost", 8025, "/websockets",WordgameServerEndpoint.class);
    try {
      server.start();
      holdOn();
    } catch (DeploymentException | IOException e) {
      e.printStackTrace();
    }finally {
      server.stop();
    }
  }

  protected static void holdOn() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please press a key to stop the server.");
    reader.readLine();
  }
}
