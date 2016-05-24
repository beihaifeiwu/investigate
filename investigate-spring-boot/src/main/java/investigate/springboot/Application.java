package investigate.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
public class Application {

  public static void main(String[] args) throws InterruptedException {
    System.setProperty("chart.path", "turnstile.txt");

    SpringApplication.run(Application.class, args);
  }
}