package com.freetmp.investigate.el;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuPin on 2015/6/3.
 */
public class ELlike {
  public static void main(String[] args) {

    String test = "I am working at ${user.dir}";

    System.out.println(StrSubstitutor.replaceSystemProperties(test));

    Map<String, String> variables = Maps.newHashMap();

    System.getProperties().forEach((key,value)->variables.put(key.toString(),value.toString()));

    System.out.println(substituteVariables(test, variables));

  }

  public static String substituteVariables(String template, Map<String, String> variables) {
    Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
    Matcher matcher = pattern.matcher(template);
    // StringBuilder cannot be used here because Matcher expects StringBuffer
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      if (variables.containsKey(matcher.group(1))) {
        String replacement = variables.get(matcher.group(1));
        // quote to work properly with $ and {,} signs
        matcher.appendReplacement(buffer, replacement != null ? Matcher.quoteReplacement(replacement) : "null");
      }
    }
    matcher.appendTail(buffer);
    return buffer.toString();
  }
}
