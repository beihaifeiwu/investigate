package com.freetmp.investigate.qi4j.helloworld3;

import org.qi4j.api.composite.TransientComposite;
import org.qi4j.api.mixin.Mixins;

/**
 * Created by LiuPin on 2015/4/29.
 */
@Mixins({HelloWorldMixin.class})
public interface HelloWorldComposite extends TransientComposite {
    String say();
}
