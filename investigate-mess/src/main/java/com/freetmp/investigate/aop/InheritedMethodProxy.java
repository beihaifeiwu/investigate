package com.freetmp.investigate.aop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

class Parent {
  public void test3(){
    System.out.println("parent test3 method");
  }
}

@Component
@Scope("prototype")
public class InheritedMethodProxy extends Parent {

}
