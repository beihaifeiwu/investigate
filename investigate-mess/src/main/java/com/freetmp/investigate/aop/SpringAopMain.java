package com.freetmp.investigate.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freetmp.investigate.java8.NewFeatureTest;

/**
 * 测试Spring的Aop
 * @author Pin Liu
 * @编写日期 2014年12月8日下午2:18:03
 */
public class SpringAopMain {
  
  public void test1(){
    System.out.println("in the method test1");
  }

  public static void main(String[] args) throws NoSuchMethodException, SecurityException {
    @SuppressWarnings("resource")
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/freetmp/investigate/aop/aop.xml");
    SpringAopMain sam = (SpringAopMain) context.getBean("springAopMain");
    sam.test1();
    NewFeatureTest newFeatureTest = (NewFeatureTest) context.getBean("newFeatureTest");
    newFeatureTest.accessLocals();
    newFeatureTest.time();
    AnnotationCglibProxy acp = context.getBean(AnnotationCglibProxy.class);
    acp.test2();
    acp.test4();
    InheritedMethodProxy imp = context.getBean(InheritedMethodProxy.class);
    imp.test3();
  }

}
