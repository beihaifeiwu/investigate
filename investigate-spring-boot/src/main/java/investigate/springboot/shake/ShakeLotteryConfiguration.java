package investigate.springboot.shake;

import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ShakeLotteryConfiguration extends WebMvcConfigurerAdapter{

  @Bean
  public CacheManager cacheManager(){
    log.info("initializing guava cache manager");
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    GuavaCache agent = new GuavaCache(
        "wechat",
        CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(20000L).build()
    );
    cacheManager.setCaches(Arrays.asList(agent));
    return cacheManager;
  }
}