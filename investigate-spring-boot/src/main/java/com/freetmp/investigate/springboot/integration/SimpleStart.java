package com.freetmp.investigate.springboot.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by liupin on 2015/8/20.
 */
@Configuration
@IntegrationComponentScan
public class SimpleStart {

  @MessagingGateway
  public interface Upcase {

    @Gateway(requestChannel = "upcase.input")
    Collection<String> upcase(Collection<String> strings);
  }

  @Bean
  public IntegrationFlow upcase(){
    return f -> f.split().<String, String>transform(String::toUpperCase).aggregate();
  }

  @Component @Order(100)
  public static class SimpleStartRun implements CommandLineRunner {

    @Autowired Upcase upcase;

    @Override
    public void run(String... args) throws Exception {
      System.out.println("******Spring Integration Simple Start*******");
      System.out.println(upcase.upcase(Arrays.asList("foo","bar")));
    }
  }

}
