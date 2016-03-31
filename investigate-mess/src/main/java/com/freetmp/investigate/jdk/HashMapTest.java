package com.freetmp.investigate.jdk;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by LiuPin on 2016/3/31.
 */
public class HashMapTest {

  public static void main(String[] args) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("a", null);
    map.put("b", null);
    map.put("c", null);
    map.put("d", null);

    System.out.println(map.containsKey("a"));

    System.out.println(map.get("a"));


    HashSet<String> set = new HashSet<>();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add(null);

    System.out.println(set.contains(null));

  }

}
