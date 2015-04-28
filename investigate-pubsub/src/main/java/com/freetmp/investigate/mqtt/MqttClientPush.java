package com.freetmp.investigate.mqtt;

import com.freetmp.investigate.transport.Protocol;
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
        mqtt.setUserName("username_temp");
        mqtt.setPassword("password_temp");

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        int i = 10000;
        Protocol.Location location = Protocol.Location.newBuilder()
                .setFloorId(1108)
                .setIdType(Protocol.IdentityType.MAC)
                .setX(0d)
                .setY(0d)
                .setTimestamp(System.currentTimeMillis())
                .setExpiresIn(60000)
                .setIdData("f8:a4:5f:3c:88:87")
                .build();
        while(i > 0) {
            connection.publish("15/1108/f8:a4:5f:3c:88:87", location.toByteArray(), QoS.AT_MOST_ONCE, false);
            i--;
            Thread.sleep(4000);
        }
    }
}
