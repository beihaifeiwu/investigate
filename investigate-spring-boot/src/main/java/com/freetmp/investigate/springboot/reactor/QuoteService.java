package com.freetmp.investigate.springboot.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.bus.EventBus;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static reactor.bus.selector.Selectors.$;

@Service
public class QuoteService {

  @Autowired
  EventBus eventBus;

  CountDownLatch latch;

  RestTemplate restTemplate = new RestTemplate();

  @PostConstruct
  public void init() {
    eventBus.on($("quotes"), this::fetchQuote);
  }

  public void fetchQuote(Event<Integer> ev) {
    try {
      QuoteResource quoteResource = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);
      System.out.println("Quote " + ev.getData() + ": " + quoteResource.getValue().getQuote());
    } finally {
      latch.countDown();
    }
  }

  public void publishQuotes(int numberOfQuotes) throws InterruptedException {
    long start = System.currentTimeMillis();

    latch = new CountDownLatch(numberOfQuotes);
    AtomicInteger counter = new AtomicInteger(1);

    for (int i = 0; i < numberOfQuotes; i++) {
      eventBus.notify("quotes", Event.wrap(counter.getAndIncrement()));
    }

    latch.await();

    long elapsed = System.currentTimeMillis() - start;

    System.out.println("Elapsed time: " + elapsed + "ms");
    System.out.println("Average time per quote: " + elapsed / numberOfQuotes + "ms");
  }

}