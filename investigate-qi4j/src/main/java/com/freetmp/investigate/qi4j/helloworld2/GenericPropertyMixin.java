package com.freetmp.investigate.qi4j.helloworld2;

import org.qi4j.api.common.AppliesTo;
import org.qi4j.api.common.AppliesToFilter;
import org.qi4j.api.injection.scope.State;
import org.qi4j.api.property.Property;
import org.qi4j.api.property.StateHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by LiuPin on 2015/4/29.
 */
@AppliesTo(GenericPropertyMixin.PropertyFilter.class)
public class GenericPropertyMixin implements InvocationHandler {

    @State private StateHolder state;

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return state.propertyFor(method);
    }

    public static class PropertyFilter implements AppliesToFilter{

        @Override public boolean appliesTo(Method method, Class<?> mixin, Class<?> compositeType, Class<?> fragmentClass) {
            return Property.class.isAssignableFrom(method.getReturnType());
        }
    }
}
