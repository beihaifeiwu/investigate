package com.palmap;

import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class ShakeLotteryApplication extends WebMvcConfigurerAdapter{

  @Bean
  public CacheManager cacheManager(){
    log.info("initializing guava cache manager");
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    GuavaCache agent = new GuavaCache(
        "agent",
        CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(20000L).build()
    );
    cacheManager.setCaches(Arrays.asList(agent));
    return cacheManager;
  }

  public static void main(String[] args) {
    SpringApplication.run(ShakeLotteryApplication.class, args);
  }
}
