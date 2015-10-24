package com.freetmp.investigate.jdk;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liupin on 2015/10/17.
 */
public class ReflectionMethod {

  interface Say {
    String say();
  }

  static class Parent implements Say {
    public String say(){
      return "I am father";
    }
  }

  static class Son extends Parent {
    @Override
    public String say() {
      return "I am son";
    }
  }

  public static void main(String[] args) throws
      NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Method method = Say.class.getMethod("say");

    // normal method invoke
    System.out.println(method.invoke(new Son()));

    // JDK dynamic proxy
    Object object = Proxy.newProxyInstance(
        ReflectionMethod.class.getClassLoader(),
        new Class[]{Say.class},
        (p, m, a) -> m.invoke(new Son(),a));

    System.out.println(method.invoke(object));

    // Cglib dynamic proxy
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(Son.class);
    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(obj, args);
      }
    });
    object = enhancer.create();

    System.out.println(method.invoke(object));

  }
}
