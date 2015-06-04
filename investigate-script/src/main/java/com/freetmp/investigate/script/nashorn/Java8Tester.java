package com.freetmp.investigate.script.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by pin on 2015/5/12.
 */
public class Java8Tester {

  public static void main(String[] args) throws ScriptException {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    InputStream inputStream = Java8Tester.class.getResourceAsStream("/com/freetmp/investigate/script/nashorn/Download.js");
    InputStreamReader reader = new InputStreamReader(inputStream);
    engine.eval(reader);
  }
}
