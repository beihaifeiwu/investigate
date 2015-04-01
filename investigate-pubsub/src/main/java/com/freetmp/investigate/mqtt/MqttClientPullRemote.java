package com.freetmp.investigate.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freetmp.investigate.transport.Protocol;
import de.undercouch.bson4jackson.BsonFactory;
import org.fusesource.mqtt.client.*;

/**
 * Created by LiuPin on 2015/3/17.
 */
public class MqttClientPullRemote {
    public static void main(String[] args) throws Exception {
        newConnection();

    }

    private static void nextConnection() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("192.168.3.190", 1883);


        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();

        while(true){
            print(connection,"Remote");
        }
    }

    private static void print(BlockingConnection connection,String mark) throws Exception {
        Message message = connection.receive();
        System.out.println("*************************" + mark + "*************************");
        System.out.println(Protocol.Location.parseFrom(message.getPayload()));
        message.ack();
    }

    private static void newConnection() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("192.168.3.190",1885);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setWillQos(QoS.AT_MOST_ONCE);

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        Topic[] topics = {new Topic("1108",QoS.AT_MOST_ONCE)};
        byte[] qoses = connection.subscribe(topics);

        System.out.println("Connection to the mqtt server");

        ObjectMapper mapper = new ObjectMapper(new BsonFactory());

        while(true){
            print(connection,"Remote");
        }
    }

}
