package com.freetmp.investigate.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.util.Properties;

import static org.apache.log4j.PatternLayout.TTCC_CONVERSION_PATTERN;

/**
 * Created by LiuPin on 2016/3/29.
 */
public class KafkaTest {

  public static void main(String[] args) throws IOException {
    ConsoleAppender console = new ConsoleAppender();
    console.setLayout(new PatternLayout(TTCC_CONVERSION_PATTERN));
    console.setThreshold(Level.INFO);
    console.activateOptions();
    Logger.getRootLogger().addAppender(console);

    Properties props = new Properties();
    props.load(KafkaTest.class.getClassLoader().getResourceAsStream("kafka.properties"));

    Producer producer = new Producer(new ProducerConfig(props));

    long marker = System.currentTimeMillis();
    long count = 10000;
    if (args.length > 0) {
      try {
        count = Long.parseLong(args[0]);
      } catch (Exception e) {
        count = 10000;
      }
    }

    try {
      for (int i = 0; i < count; i++) {
        String content = "I am " + i + "th test message";
        //noinspection unchecked
        producer.send(new KeyedMessage("test-topic", content.getBytes()));
      }
      producer.close();
    } finally {
      long current = System.currentTimeMillis();
      System.out.println("Total send messages[" + count + "] used time " + (current - marker) + "ms / " + (current - marker)/1000 + "s / " + (current - marker)/1000/60 + "m");
    }
  }
}
