package com.freetmp.investigate.qi4j.helloworld;

import org.qi4j.api.concern.Concerns;
import org.qi4j.api.mixin.Mixins;

/**
 * Created by LiuPin on 2015/4/28.
 */
@Mixins(SpeakerMixin.class)
@Concerns(ModalConcern.class)
public interface Speaker {
    String sayHello();
}
