package com.freetmp.investigate.activemq;

import org.apache.qpid.proton.amqp.messaging.Source;
import org.apache.qpid.proton.amqp.messaging.Target;
import org.apache.qpid.proton.hawtdispatch.api.*;

import java.net.URISyntaxException;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by LiuPin on 2015/4/1.
 */
public class AmqpMessager {

    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        AmqpConnectOptions options = new AmqpConnectOptions();
        options.setHost("127.0.0.1",5445);
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

        synchronized (options) {
            options.wait(MAX_VALUE);
        }

        AmqpSession session = connection.createSession();

        connection.queue().execute(() -> {
            Source source = new Source();
            source.setAddress("queue://location");
            AmqpReceiver receiver = session.createReceiver(source);

            receiver.setDeliveryListener((msg) -> {
                System.out.println("received msg : " + msg);
                msg.settle();
            });
        });

        Target target = new Target();
        target.setAddress("queue://location");
        AmqpSender sender = session.createSender(target);

        for (int i = 0; i < 1000; i++) {
            String message = "test " + i;
            MessageDelivery delivery = sender.send(session.createTextMessage(message));
            System.out.println("Sender : " + message );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
