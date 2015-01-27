package com.freetmp.investigate.websocket;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static io.undertow.websockets.jsr.WebSocketDeploymentInfo.ATTRIBUTE_NAME;

/**
 * websocket服务器
 * @author Pin Liu
 * @编写日期 2015年1月14日上午11:42:02
 */
public class WebSocketUndertowServer {
  
  public static void main(String...args){
    runUndertowServer();
  }

  protected static void holdOn() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please press a key to stop the server.");
    reader.readLine();
  }
  /**
   * 使用undertow服务器启动websocket服务
   * @author Pin Liu
   * @编写日期: 2015年1月14日下午1:15:59
   */
  public static void runUndertowServer(){
    Undertow server = null;
    try {
      WebSocketDeploymentInfo deploymentInfo = new WebSocketDeploymentInfo()
                                                          .addEndpoint(WordgameServerEndpoint.class);
      
      DeploymentInfo websocketDeployment = Servlets.deployment().setContextPath("/websockets")
                                                                .addServletContextAttribute(ATTRIBUTE_NAME, deploymentInfo)
                                                                .setDeploymentName("word game")
                                                                .setClassLoader(WebSocketUndertowServer.class.getClassLoader());
      
      DeploymentManager manager = Servlets.defaultContainer().addDeployment(websocketDeployment);
      
      manager.deploy();
      
      server = Undertow.builder()
                                .addHttpListener(8025, "localhost")
                                .setHandler(Handlers.path().addPrefixPath("/websockets", manager.start()))
                                .build();
      server.start();
      holdOn();
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      server.stop();
    }
  }
}
