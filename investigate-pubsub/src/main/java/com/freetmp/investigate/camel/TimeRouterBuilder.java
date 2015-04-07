package com.freetmp.investigate.camel;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by LiuPin on 2015/4/7.
 */
public class TimeRouterBuilder extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(TimeRouterBuilder.class);

    @Override
    public void configure() throws Exception {
        from("timer://timer?period=1000").process(exchange -> LOG.info("Processing {}", exchange));
    }
}
