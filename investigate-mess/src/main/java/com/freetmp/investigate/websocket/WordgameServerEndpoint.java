package com.freetmp.investigate.websocket;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于websoket互动游戏的服务端
 * @author Pin Liu
 * @编写日期 2015年1月14日上午11:19:42
 */
@ServerEndpoint("/game")
public class WordgameServerEndpoint {

  private static final Logger log = LoggerFactory.getLogger(WordgameServerEndpoint.class);
  
  @OnOpen
  public void onOpen(Session session){
    log.info("Connected ... " + session.getId());
  }
  
  @OnMessage
  public String onMessage(String message, Session session){
    switch(message){
    case "start":
      log.info("Starting the game by sending first word");
      String scrambledWord = WordRepository.getInstance().getRandomWord().getScrambledWord();
      session.getUserProperties().put("scrambledWord", scrambledWord);
      return scrambledWord;
    case "quit":
      try{
        session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
      } catch (IOException e){
        throw new RuntimeException(e);
      }
      break;
    }
    String scrambledWord = (String) session.getUserProperties().get("scrambledWord");
    return checkLastWordAndSendANewWord(scrambledWord, message, session);
  }
  
  @OnClose
  public void onClose(Session session, CloseReason closeReason){
    log.info("Session {} closed because of {}", session.getId(),closeReason);
  }
  
  private String checkLastWordAndSendANewWord(String scrambledWord, String unScrambledWOrd, Session session){
    WordRepository repository = WordRepository.getInstance();
    Word word = repository.getWord(scrambledWord);
    
    String nextScrambleWord = repository.getRandomWord().getScrambledWord();
   
    session.getUserProperties().put("scrambleWord", nextScrambleWord);
    String correctUnscrambledWord = word.getUnscrambbledWord();
    
    if(word == null || !correctUnscrambledWord.equals(unScrambledWOrd)){
      return String.format("You guessed it wrong, Correct answer %s. Try next one .. %s", correctUnscrambledWord, nextScrambleWord);
    }
    
    return String.format("You guessed it right. Try the next word ... %s", nextScrambleWord);
  }
}
