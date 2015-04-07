package com.freetmp.investigate.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerMain {
    static Logger LOG = LoggerFactory.getLogger(TimerMain.class);

    public static void main(String[] args) throws Exception {
        new TimerMain().run();
    }

    void run() throws Exception {
        final CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(createRouteBuilder());
        camelContext.setTracing(true);
        camelContext.start();


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    camelContext.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        waitForStop();
    }

    RouteBuilder createRouteBuilder() {
        return new TimeRouterBuilder();
    }

    void waitForStop() {
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}