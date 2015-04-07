package com.freetmp.investigate.camel;

import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.main.Main;

public class TimerBeansMain extends Main {
    static Logger LOG = LoggerFactory.getLogger(TimerBeansMain.class);

    public static void main(String[] args) throws Exception {
        TimerBeansMain main = new TimerBeansMain();
        main.enableHangupSupport();
        main.bind("processByBean1", new Bean1());
        main.bind("processAgainByBean2", new Bean2());
        main.addRouteBuilder(createRouteBuilder());
        main.run(args);
    }

    static RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("timer://timer1?period=1000")
                        .to("bean:processByBean1")
                        .to("bean:processAgainByBean2");
            }
        };
    }

    // Processor beans
    static class Bean1 implements Processor {
        public void process(Exchange msg) {
            LOG.info("First process {}", msg);
        }
    }

    static class Bean2 implements Processor {
        public void process(Exchange msg) {
            LOG.info("Second process {}", msg);
        }
    }
}