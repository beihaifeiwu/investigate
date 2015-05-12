package com.freetmp.investigate.algorithm;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by LiuPin on 2015/5/12.
 */
public class AlgorithmProfileSuite {

  public void run(){
    Options opt = new OptionsBuilder()
        .include(".*" + getClass().getSimpleName() + ".*")
        .mode(Mode.AverageTime)
        .timeUnit(TimeUnit.NANOSECONDS)
        .warmupIterations(2)
        .measurementIterations(5)
        .forks(1)
        .build();

    try {
      new Runner(opt).run();
    } catch (RunnerException e) {
      e.printStackTrace();
    }
  }
}
