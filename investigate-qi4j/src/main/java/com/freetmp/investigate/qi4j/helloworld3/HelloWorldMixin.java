package com.freetmp.investigate.qi4j.helloworld3;

import org.qi4j.api.injection.scope.This;

/**
 * Created by LiuPin on 2015/4/29.
 */
public abstract class HelloWorldMixin implements HelloWorldComposite {

    @This HelloWorldState state;

    @Override public String say() {
        return state.phrase().get() + " " + state.name().get();
    }
}
