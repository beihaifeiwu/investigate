package com.freetmp.investigate.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录切面
 * @author Pin Liu
 */
@Aspect
public class LoggingAspect {

  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
  
  @Pointcut("execution(* com.freetmp.investigate.aop.SpringAopMain.test*(..))")
  public void test(){}
  
  @Pointcut("execution(* com.freetmp.investigate.java8.NewFeatureTest.*(..))")
  public void other(){}
  
  @Pointcut("execution(* *(..))")
  public void all(){}
  
  @Before("test()")
  public void beforeTest(JoinPoint point){
    Signature signature = point.getSignature();
    log.info("beforeTest {}",signature.getName());
  }
  
  @After("test()")
  public void afterTest(JoinPoint point){
    Signature signature = point.getSignature();
    log.info("afterTest {}",signature.getName());
  }
  
  @Before("all()")
  public void beforeAll(JoinPoint point){
    Signature signature = point.getSignature();
    log.info("beforeAll {}",signature.getName());
  }
  
  @After("all()")
  public void afterAll(JoinPoint point){
    Signature signature = point.getSignature();
    log.info("afterAll {}",signature.getName());
  }
  
  @Before("other()")
  public void beforeOther(JoinPoint point){
    Signature signature = point.getSignature();
    log.info("beforeOther {}",signature.getName());
  }
  
  @After("other()")
  public void afterOther(JoinPoint point){
    Signature signature = point.getSignature();
    log.info("afterOther {}",signature.getName());
  }

}
