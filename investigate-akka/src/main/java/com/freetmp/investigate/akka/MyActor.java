package com.freetmp.investigate.akka;

import akka.actor.UntypedActor;

/**
 * Created by LiuPin on 2015/5/6.
 */
public class MyActor extends UntypedActor {
  @Override public void onReceive(Object o) throws Exception {
    System.out.println(o);
  }
}
