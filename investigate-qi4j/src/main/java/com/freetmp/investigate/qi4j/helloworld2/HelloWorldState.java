package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.common.Optional;
import org.qi4j.api.property.Property;

public interface HelloWorldState {
    @Optional Property<String> phrase();
    @Optional Property<String> name();
}