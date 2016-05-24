package investigate.springboot.jersey2;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by LiuPin on 2015/8/6.
 */
@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(GreetingOne.class);
    register(GreetingTwo.class);
  }
}
