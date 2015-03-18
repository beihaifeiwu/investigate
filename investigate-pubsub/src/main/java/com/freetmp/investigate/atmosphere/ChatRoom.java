package com.freetmp.investigate.atmosphere;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.cache.UUIDBroadcasterCache;
import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;
import org.atmosphere.interceptor.HeartbeatInterceptor;

import java.io.IOException;

/**
 * Created by LiuPin on 2015/3/18.
 */
@AtmosphereHandlerService(path = "/chat",
        broadcasterCache = UUIDBroadcasterCache.class,
        interceptors = {
                AtmosphereResourceLifecycleInterceptor.class,
                TrackMessageSizeInterceptor.class,
                BroadcastOnPostAtmosphereInterceptor.class,
                HeartbeatInterceptor.class})
public class ChatRoom extends OnMessage<String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(AtmosphereResponse response, String message) throws IOException {
        response.write(mapper.writeValueAsString(mapper.readValue(message,Data.class)));
    }
}
