package geoserver.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LiuPin on 2016/5/5.
 */
public class HelloWorld {

  public void sayHello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.getOutputStream().write("Hello World".getBytes());
  }

  public static void main(String[] args) {

  }
}
