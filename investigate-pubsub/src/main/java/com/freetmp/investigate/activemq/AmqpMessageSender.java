package com.freetmp.investigate.activemq;

import org.apache.qpid.proton.amqp.messaging.Target;
import org.apache.qpid.proton.hawtdispatch.api.*;

import java.net.URISyntaxException;

/**
 * Created by LiuPin on 2015/3/28.
 */
public class AmqpMessageSender {
    public static void main(String[] args) throws InterruptedException, URISyntaxException {

        AmqpConnectOptions options = new AmqpConnectOptions();
        options.setHost("localhost", 5445);
        options.setUser("admin");
        options.setPassword("password");

        AmqpConnection connection = AmqpConnection.connect(options);

        connection.queue().execute(() -> {
            connection.onConnected(new Callback<Void>() {
                @Override
                public void onSuccess(Void value) {
                    System.out.println("connected success: " + value);
                    synchronized (options) {
                        options.notify();
                    }
                }

                @Override
                public void onFailure(Throwable value) {
                    System.out.println("connected failure: " + value);
                    synchronized (options) {
                        options.notify();
                    }
                }
            });
        });

        synchronized (options){
            options.wait(Integer.MAX_VALUE);
        }


        Target target = new Target();
        target.setAddress("queue://location");

        AmqpSession session = connection.createSession();
        AmqpSender sender = session.createSender(target);

        for(int i = 0; i < 1000; i++){
            sender.send(session.createTextMessage("test " + i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
