package geoserver.web;

import org.geoserver.rest.ReflectiveResource;

public class HelloReflectiveResource extends ReflectiveResource {

   @Override
   protected Object handleObjectGet() throws Exception {
       return new Hello("Hello World");
   }
}