package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.injection.scope.This;

/**
 * Created by LiuPin on 2015/4/29.
 */
public class HelloWorldBehaviourMixin implements HelloWorldBehaviour {

    @This HelloWorldState state;

    @Override public String say() {
        return state.phrase().get() + " " + state.name().get();
    }
}
