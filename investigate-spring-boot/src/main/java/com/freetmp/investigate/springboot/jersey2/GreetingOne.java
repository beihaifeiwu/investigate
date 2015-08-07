package com.freetmp.investigate.springboot.jersey2;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by LiuPin on 2015/8/6.
 */
@Component
@Path("/hello")
public class GreetingOne {

  @GET
  public String message(){
    return  "Hello";
  }
}
