package com.freetmp.investigate.mqtt;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import java.net.URISyntaxException;

/**
 * Created by LiuPin on 2015/3/17.
 */
public class MqttClientPush {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        int i = 10000;
        while(i > 0) {
            connection.publish("foo", "Hello".getBytes(), QoS.AT_MOST_ONCE, false);
            i--;
            Thread.sleep(100);
        }
    }
}
