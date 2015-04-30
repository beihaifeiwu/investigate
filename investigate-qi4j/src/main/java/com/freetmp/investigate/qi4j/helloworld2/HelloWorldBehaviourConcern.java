package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.concern.ConcernOf;

/**
 * Created by LiuPin on 2015/4/29.
 */
public class HelloWorldBehaviourConcern extends ConcernOf<HelloWorldBehaviour> implements HelloWorldBehaviour {
    @Override public String say() {
        return "Simon says:\"" + next.say() + "\"";
    }
}
