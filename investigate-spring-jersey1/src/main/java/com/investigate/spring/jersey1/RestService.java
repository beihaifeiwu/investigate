package com.investigate.spring.jersey1;

import com.sun.jersey.api.container.ContainerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.spring.container.SpringComponentProviderFactory;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiuPin on 2015/4/7.
 */
@Service
public class RestService {

  private static final Logger LOG = LoggerFactory.getLogger(RestService.class);

  private ExecutorService executor = Executors.newSingleThreadExecutor();

  private HttpServer server;

  @Autowired
  private ApplicationContext context;

  @PostConstruct
  public void start() {
    server = new HttpServer();
    final NetworkListener listener = new NetworkListener("grizzly", NetworkListener.DEFAULT_NETWORK_HOST, 9998);
    listener.setSecure(false);
    server.addListener(listener);
    ResourceConfig rc = new ClassNamesResourceConfig(AuthenticationResource.class, AuthorizationResource.class);

    // Let Jersey know about our existing context
    SpringComponentProviderFactory handler = new SpringComponentProviderFactory(rc, (ConfigurableApplicationContext) context);
    HttpHandler processor = ContainerFactory.createContainer(HttpHandler.class, rc, handler);
    server.getServerConfiguration().addHttpHandler(processor, "/rest");

    executor.execute(() -> {
      while (true) {
        try {
          server.start();
        } catch (Exception e) {
          LOG.warn("Authorize Rest Service went error, will be restart again", e);
          server.stop();
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e1) {
            LOG.warn("server waiting was interrupted", e1);
          }
        }
      }
    });
  }

  @PreDestroy
  public void stop() {
    if (server != null) server.stop();
    executor.shutdown();
  }
}
