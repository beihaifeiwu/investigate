package com.freetmp.investigate.springboot.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

import java.util.concurrent.CountDownLatch;

import static reactor.event.selector.Selectors.$;

/**
 * Created by liupin on 2015/8/20.
 */
@Configuration
public class ReactorConfig {
  private static final int NUMBER_OF_QUOTES = 10;

  @Bean
  Environment env() {
    return new Environment();
  }

  @Bean
  Reactor createReactor(Environment env) {
    return Reactors.reactor()
        .env(env)
        .dispatcher(Environment.THREAD_POOL)
        .get();
  }

  @Bean
  public CountDownLatch latch() {
    return new CountDownLatch(NUMBER_OF_QUOTES);
  }

  @Component
  public static class ReactorRun implements CommandLineRunner {
    @Autowired
    private Reactor reactor;

    @Autowired
    private Receiver receiver;

    @Autowired
    private Publisher publisher;

    @Override
    public void run(String... args) throws Exception {
      System.out.println("******Spring Reactor Quotes Start*******");
      reactor.on($("quotes"), receiver);
      publisher.publishQuotes(NUMBER_OF_QUOTES);
    }
  }
}
