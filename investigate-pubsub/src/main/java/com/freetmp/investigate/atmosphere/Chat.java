package com.freetmp.investigate.atmosphere;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by LiuPin on 2015/3/18.
 */
@ManagedService(path = "/chat2")
public class Chat {
    private final Logger logger = LoggerFactory.getLogger(Chat.class);

    @Ready
    public void onReady(final AtmosphereResource resource){
        logger.info("Brower {} connected.", resource.uuid());
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event){
        if(event.isCancelled()){
            logger.info("Browser {} unexpectedly disconnected",event.getResource().uuid());
        } else if(event.isClosedByClient()){
            logger.info("Browser {} closed the connection",event.getResource().uuid());
        }
    }

    @Message(encoders = {JacksonEncoder.class},decoders = {JacksonDecoder.class})
    public Data onMessage(Data message) throws IOException{
        logger.info("{} just send {}",message.getAuthor(),message.getMessage());
        return message;
    }
}
