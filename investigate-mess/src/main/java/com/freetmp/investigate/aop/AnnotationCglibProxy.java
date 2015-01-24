package com.freetmp.investigate.aop;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AnnotationCglibProxy {
  
  @Resource
  private InheritedMethodProxy proxy;
  
  public void test2(){
    System.out.println("annotation cglib proxy");
  }
  
  public void test4(){
    System.out.println("annotation injected proxy");
    proxy.test3();
  }

}
