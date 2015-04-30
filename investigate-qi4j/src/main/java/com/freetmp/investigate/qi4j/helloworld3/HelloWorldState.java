package com.freetmp.investigate.qi4j.helloworld3;

import org.qi4j.api.property.Property;
import org.qi4j.library.constraints.annotation.NotEmpty;

/**
 * Created by LiuPin on 2015/4/29.
 */
public interface HelloWorldState {
    @NotEmpty Property<String> phrase();
    @NotEmpty Property<String> name();
}
