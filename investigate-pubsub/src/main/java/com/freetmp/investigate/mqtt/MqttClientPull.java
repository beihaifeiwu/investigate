package com.freetmp.investigate.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freetmp.investigate.transport.Protocol;
import de.undercouch.bson4jackson.BsonFactory;
import org.fusesource.mqtt.client.*;

/**
 * Created by LiuPin on 2015/3/17.
 */
public class MqttClientPull {

    public static void another() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("192.168.3.190", 1883);
        mqtt.setClientId("MqttClientTest");

        BlockingConnection connection = mqtt.blockingConnection();

        for(int i = 0; i < 10; i++){
            print(connection,"REMOTE");
        }
    }

    public static void main(String[] args) throws Exception {
/*        ExecutorService first = Executors.newSingleThreadExecutor();
        first.execute(() -> {
            try {
                MqttClientPull.local();
                synchronized (first){
                    first.notify();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        synchronized (first) {
            first.wait();
            first.shutdownNow();
        }
        another();*/

        newConnection();
    }

    public static void local() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setClientId("MqttClientTest");
        mqtt.setWillQos(QoS.AT_MOST_ONCE);

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        Topic[] topics = {new Topic("1108",QoS.AT_MOST_ONCE)};
        byte[] qoses = connection.subscribe(topics);

        for( int i = 0; i< 10; i++){
            print(connection,"LOCAL");
        }
    }

    private static void newConnection() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost",1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setWillQos(QoS.AT_MOST_ONCE);

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        Topic[] topics = {new Topic("15/#",QoS.AT_MOST_ONCE)};
        byte[] qoses = connection.subscribe(topics);

        System.out.println("Connection to the mqtt server");

        ObjectMapper mapper = new ObjectMapper(new BsonFactory());

        while(true){
            print(connection,"LOCAL");
        }
    }

    private static void printRaw(BlockingConnection connection,String mark) throws Exception {
        Message message = connection.receive();
        System.out.println("*************************" + mark + "*************************");
        System.out.println(new String(message.getPayload()));
        message.ack();
    }

    private static void print(BlockingConnection connection,String mark) throws Exception {
        Message message = connection.receive();
        System.out.println("*************************"+mark+"*************************");
        System.out.println(Protocol.Location.parseFrom(message.getPayload()));
        message.ack();
    }
}
