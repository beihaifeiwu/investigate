package com.freetmp.investigate.griffon;

import groovy.lang.GroovyShell;

/**
 * Created by LiuPin on 2016/3/17.
 */
public class GroovyShellEvaluator implements Evaluator {

  private GroovyShell shell = new GroovyShell();

  @Override public Object evaluate(String input) {
    return null;
  }
}
