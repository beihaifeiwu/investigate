package com.freetmp.investigate.websocket;

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
 * 测试Notification的客户端
 * @author Pin Liu
 * @编写日期 2015年1月14日上午11:33:05
 */
@ClientEndpoint
public class NotificationTestClientEndpoint {
  
  private static final Logger log = LoggerFactory.getLogger(NotificationTestClientEndpoint.class);
  
  private static CountDownLatch latch;
  
  @OnOpen
  public void onOpen(Session session){
    log.info("Connected ... " + session.getId());
  }
  
  @OnMessage
  public void onMessage(String message, Session session){
    log.info("Received .... " + message);
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
      client.connectToServer(NotificationTestClientEndpoint.class, new URI("ws://localhost:8080/websocket/position/1108"));
      latch.await();
    } catch (DeploymentException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
