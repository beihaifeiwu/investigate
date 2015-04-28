package com.freetmp.investigate.qi4j.helloworld;

/**
 * Created by LiuPin on 2015/4/28.
 */
public class SpeakerMixin implements Speaker {
    @Override public String sayHello() {
        return "Hello World";
    }
}
