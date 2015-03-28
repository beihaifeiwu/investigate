package com.freetmp.investigate.activemq;

import org.apache.qpid.proton.amqp.messaging.Received;
import org.apache.qpid.proton.amqp.messaging.Source;
import org.apache.qpid.proton.amqp.messaging.Target;
import org.apache.qpid.proton.hawtdispatch.api.*;

import java.net.URISyntaxException;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by LiuPin on 2015/3/28.
 */
public class AmqpMessageReceiver {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
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
            options.wait(MAX_VALUE);
        }

        connection.queue().execute(()->{
            Source source = new Source();
            source.setAddress("queue://location");

            AmqpSession session = connection.createSession();
            AmqpReceiver receiver = session.createReceiver(source);

            receiver.setDeliveryListener((msg) -> System.out.println("received msg : " + msg));
        });

        Thread.sleep(MAX_VALUE);

    }
}
