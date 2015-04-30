package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.composite.TransientComposite;
import org.qi4j.api.concern.Concerns;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.sideeffect.SideEffects;

/**
 * Created by LiuPin on 2015/4/29.
 */
@Mixins({HelloWorldBehaviourMixin.class,GenericPropertyMixin.class})
@Concerns(HelloWorldBehaviourConcern.class)
@SideEffects(HelloWorldBehaviourSideEffect.class)
public interface HelloWorldComposite extends HelloWorldBehaviour,HelloWorldState, TransientComposite {
}
