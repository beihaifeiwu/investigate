package geoserver.web;

import org.geoserver.rest.MapResource;

import java.util.Collections;
import java.util.Map;

/**
 * Created by LiuPin on 2016/5/6.
 */
public class HelloMapResource extends MapResource {

  @Override public Map getMap() throws Exception {
    return Collections.singletonMap("message", "Hello World");
  }

}
