package com.freetmp.investigate.qi4j.helloworld;

import org.qi4j.api.concern.ConcernOf;

/**
 * Created by LiuPin on 2015/4/28.
 */
public class ModalConcern extends ConcernOf<Speaker> implements Speaker {
    @Override public String sayHello() {
        return "Hehe " + next.sayHello();
    }
}
