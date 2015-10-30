package com.palmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ShakeLotteryApplication extends WebMvcConfigurerAdapter{

  public static void main(String[] args) {
    SpringApplication.run(ShakeLotteryApplication.class, args);
  }
}
