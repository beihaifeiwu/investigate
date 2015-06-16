package com.freetmp.investigate.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于websocket互动游戏的客户端
 * @author Pin Liu
 * @编写日期 2015年1月14日上午11:33:05
 */
@ClientEndpoint
public class WordgameClientEndpoint {
  
  private static final Logger log = LoggerFactory.getLogger(WordgameClientEndpoint.class);
  
  private static CountDownLatch latch;
  
  @OnOpen
  public void onOpen(Session session){
    log.info("Connected ... " + session.getId());
    try {
      session.getBasicRemote().sendText("start");
    }catch(IOException e){
      throw new RuntimeException(e);
    }
  }
  
  @OnMessage
  public String onMessage(String message, Session session){
    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    log.info("Received .... " + message);
    String userInput = null;
    try {
      userInput = bufferRead.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return userInput;
  }
  
  @OnClose
  public void onClose(Session session, CloseReason closeReason){
    log.info("Session {} close because of {}",session.getId(),closeReason);
    latch.countDown();
  }
  
  public static void main(String...strings ){
    latch = new CountDownLatch(1);
    ClientManager client = ClientManager.createClient();
    try {
      client.connectToServer(WordgameClientEndpoint.class, new URI("ws://localhost:8025/websockets/game"));
      latch.await();
    } catch (DeploymentException | InterruptedException | URISyntaxException | IOException e) {
      e.printStackTrace();
    }
  }
}
