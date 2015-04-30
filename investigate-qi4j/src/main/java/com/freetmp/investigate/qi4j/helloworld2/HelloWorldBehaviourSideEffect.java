package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.sideeffect.SideEffectOf;

/**
 * Created by LiuPin on 2015/4/29.
 */
public class HelloWorldBehaviourSideEffect extends SideEffectOf<HelloWorldBehaviour> implements HelloWorldBehaviour {
    @Override public String say() {
        System.out.println(result.say());
        return null;
    }
}
