package com.freetmp.investigate.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liupin on 2015/11/7.
 */
public class ThreadTest implements Runnable {

  private int id = 0;

  @Override
  public void run() {
    id++;
  }

  public static void main(String[] args) {
    // 获取当前程序运行时对象
    Runtime run = Runtime.getRuntime();
    // 调用垃圾回收机制，以减少内存误差
    run.gc();
    // 获取当前JVM的空闲内存
    long freeMemory = run.freeMemory();
    // 系统当前时间
    long timePro = System.currentTimeMillis();
    for (int i = 0; i < 1000; i++) {
      new Thread(new ThreadTest()).start();
    }
    System.out.println("独立创建并执行1000个线程所需要占用的内存大小: "
        + (freeMemory - run.freeMemory()));
    System.out.println("独立创建并运行1000个线程需要的时间为: "
        + (System.currentTimeMillis() - timePro));
    System.out.println("------------------------------------");
    // 获取当前程序运行时对象
    Runtime run2 = Runtime.getRuntime();
    // 调用垃圾回收机制，以减少内存误差
    run.gc();
    // 获取当前JVM的空闲内存
    long freeMemory2 = run.freeMemory();
    // 系统当前时间
    long timePro2 = System.currentTimeMillis();
    ExecutorService service = Executors.newFixedThreadPool(2);
    for (int i = 0; i < 1000; i++) {
      new Thread(new ThreadTest()).start();
    }
    System.out.println("创建线程池并执行1000个线程所需要占用的内存大小: "
        + (freeMemory2 - run2.freeMemory()));
    service.shutdown();
    System.out.println("创建线程池独立创建并运行1000个线程需要的时间为: "
        + (System.currentTimeMillis() - timePro2));
  }
}
