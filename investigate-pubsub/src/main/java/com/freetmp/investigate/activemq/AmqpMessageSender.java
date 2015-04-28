package com.freetmp.investigate.activemq;

import com.freetmp.investigate.transport.Protocol;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.qpid.amqp_1_0.client.ConnectionException;
import org.apache.qpid.amqp_1_0.client.LinkDetachedException;
import org.apache.qpid.amqp_1_0.client.Message;
import org.apache.qpid.amqp_1_0.client.Sender;
import org.apache.qpid.proton.amqp.messaging.ApplicationProperties;
import org.apache.qpid.proton.amqp.messaging.Target;
import org.apache.qpid.proton.hawtdispatch.api.*;

import javax.jms.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static javax.jms.DeliveryMode.NON_PERSISTENT;

/**
 * Created by LiuPin on 2015/3/28.
 */
public class AmqpMessageSender {
    public static void main(String[] args) throws InterruptedException, URISyntaxException, JMSException, ConnectionException, LinkDetachedException, TimeoutException, Sender.SenderCreationException {

        //useQpid();
        //useActiveMq();
        //useQpidClient();
        useQpidWithProperty();
    }

    private static void useActiveMq() throws JMSException {
        // Create a ConnectionFactory，创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        // Create a Connection，创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();//打开连接

        // Create a Session//创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//指定ACK_Mode签收确认模式为自动确认

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");//创建消息目标(点对点模型队列)
        //Destination destination = session.createTopic("TEST.FOO");//创建消息目标(订阅主题)
        // Create a MessageProducer from the Session to the Topic or Queue,创建消息生产者
        MessageProducer producer = session.createProducer(destination);//创建消息生产者
        producer.setDeliveryMode(NON_PERSISTENT);//指定传输模式-非持久性消息

        // Create a messages，创建消息
        String text = "Hello world! From: " + Thread.currentThread().getName();
        TextMessage message = session.createTextMessage(text);//创建文本消息

        // Tell the producer to send the message
        System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
        producer.send(message);//发送消息

        // Clean up
        session.close();//关闭会话
        connection.close();//关闭连接
    }

    private static void useQpidClient() throws ConnectionException, Sender.SenderCreationException, TimeoutException, LinkDetachedException, InterruptedException {
        org.apache.qpid.amqp_1_0.client.Connection connection = new org.apache.qpid.amqp_1_0.client.Connection("127.0.0.1", 5445, "admin", "password");
        connection.awaitOpen();
        org.apache.qpid.amqp_1_0.client.Session session = connection.createSession();
        Sender sender = session.createSender("queue://location");
        while(true){
            sender.send(new Message("hello"));
            Thread.sleep(1000);
        }
    }

    private static void useQpid() throws URISyntaxException, InterruptedException {
        AmqpConnectOptions options = new AmqpConnectOptions();
        options.setHost("127.0.0.1", 5445);
        options.setUser("username_temp");
        options.setPassword("password_temp");
        // 避免ClinetID为空的异常发生
        options.setRemoteContainerId("investigate-sender");

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
            options.wait(Integer.MAX_VALUE);
        }


        Target target = new Target();
        target.setAddress("topic://15.1108.f8:a4:5f:3c:88:87");

        AmqpSession session = connection.createSession();
        AmqpSender sender = session.createSender(target);

        for (int i = 0; i < 1000; i++) {
            String messsage = "test " + i;
            MessageDelivery delivery = sender.send(session.createTextMessage(messsage));
            System.out.println("Sender : " + messsage );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void useQpidWithProperty() throws URISyntaxException, InterruptedException {
        AmqpConnectOptions options = new AmqpConnectOptions();
        options.setHost("127.0.0.1",5445);
        options.setUser("admin");
        options.setPassword("password");
        // 避免ClinetID为空的异常发生
        options.setRemoteContainerId("investigate-sender");

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
            options.wait(Integer.MAX_VALUE);
        }


        Target target = new Target();
        target.setAddress("topic://dispatch");

        AmqpSession session = connection.createSession();
        AmqpSender sender = session.createSender(target);

        Protocol.Location location = Protocol.Location.newBuilder()
                .setFloorId(1108)
                .setIdType(Protocol.IdentityType.MAC)
                .setX(0d)
                .setY(0d)
                .setTimestamp(System.currentTimeMillis())
                .setExpiresIn(60000)
                .setIdData("f8:a4:5f:3c:88:87")
                .build();

        org.apache.qpid.proton.message.Message message = session.createBinaryMessage(location.toByteArray());
        Map<String,Object> map = new HashMap<>();
        map.put("floorId",location.getFloorId());
        map.put("mac",location.getIdData());
        message.setApplicationProperties(new ApplicationProperties(map));

        sender.send(message);

        Thread.sleep(1000);

        session.close();
    }

}
