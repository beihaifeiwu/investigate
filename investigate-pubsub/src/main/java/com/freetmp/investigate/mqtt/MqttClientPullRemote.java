package com.freetmp.investigate.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.fusesource.mqtt.client.*;

/**
 * Created by LiuPin on 2015/3/17.
 */
public class MqttClientPullRemote {
    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("192.168.3.190",5445);
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
            Message message = connection.receive();
            System.out.print(message.getTopic());
            System.out.println(" : " + mapper.readValue(message.getPayload(), LocationData.class));
            message.ack();
        }

    }
}
