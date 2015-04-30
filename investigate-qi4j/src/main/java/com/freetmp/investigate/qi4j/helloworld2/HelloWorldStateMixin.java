package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.injection.scope.State;
import org.qi4j.api.property.Property;

/**
 * Created by LiuPin on 2015/4/29.
 */
public class HelloWorldStateMixin implements HelloWorldState {

    @State Property<String> phrase;
    @State Property<String> name;

    @Override public Property<String> phrase() {
        return phrase;
    }

    @Override public Property<String> name() {
        return name;
    }
}
