package com.freetmp.investigate.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.qpid.amqp_1_0.client.*;
import org.apache.qpid.proton.amqp.messaging.Source;
import org.apache.qpid.proton.hawtdispatch.api.*;

import javax.jms.*;
import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.Session;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by LiuPin on 2015/3/28.
 */
public class AmqpMessageReceiver {

    public static void main(String[] args) throws URISyntaxException, InterruptedException, JMSException, ConnectionException, TimeoutException {
        useQpid();
        //useActiveMq();
        //useQpidClient();
    }

    private static void useActiveMq() throws JMSException {
// Create a ConnectionFactory，创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

// Create a Connection，创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();//打开连接

        connection.setExceptionListener(e -> e.printStackTrace());//指定连接使用的异常监听器

// Create a Session，创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //指定ACK_Mode签收确认模式为自动确认


// Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");//创建消息目标(点对点模型队列)
//Destination destination = session.createTopic("TEST.FOO");//创建消息目标(订阅主题)

// Create a MessageConsumer from the Session to the Topic or Queue//创建消息消费者
        MessageConsumer consumer = session.createConsumer(destination);

// Wait for a message
        Message message = consumer.receive(1000);//接收1000毫秒内到达的消息，如果没有收到此方法将阻塞等待直到指定超时时间

        if (message instanceof TextMessage) {//判断消息类型是否为文本消息
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("Received: " + text);
        } else {
            System.out.println("Received: " + message);
        }
        consumer.close();//关闭消费者
        session.close();//关闭会话
        connection.close();//关闭连接
    }

    private static void useQpidClient() throws ConnectionException, TimeoutException, InterruptedException {
        org.apache.qpid.amqp_1_0.client.Connection connection = new org.apache.qpid.amqp_1_0.client.Connection("127.0.0.1",5445,"admin","password");
        connection.awaitOpen();
        org.apache.qpid.amqp_1_0.client.Session session = connection.createSession();
        Receiver receiver = session.createReceiver("queue://location");
        while (true){
            org.apache.qpid.amqp_1_0.client.Message message = receiver.receive(true);
            System.out.println(message.getPayload());
            message.setSettled(true);
        }
    }

    private static void useQpid() throws URISyntaxException, InterruptedException {
        AmqpConnectOptions options = new AmqpConnectOptions();
        options.setHost("127.0.0.1",5445);
        options.setUser("admin");
        options.setPassword("password");
        options.setRemoteContainerId("investigate-receiver");

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

        connection.queue().execute(() -> {
            Source source = new Source();
            source.setAddress("queue://location");

            AmqpSession session = connection.createSession();
            AmqpReceiver receiver = session.createReceiver(source);

            receiver.setDeliveryListener((msg) -> {
                System.out.println("received msg : " + msg);
                msg.settle();
            });

            receiver.resume();
        });

        Thread.sleep(Integer.MAX_VALUE);
    }
}
