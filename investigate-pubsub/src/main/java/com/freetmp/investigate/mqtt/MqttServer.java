package com.freetmp.investigate.mqtt;

import org.dna.mqtt.moquette.server.Server;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by LiuPin on 2015/3/17.
 */
public class MqttServer {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("port","1883");
        properties.setProperty("host","0.0.0.0");
        properties.setProperty("websocket_port","8080");
        properties.setProperty("password_file","src/main/resources/password_file.conf");

        System.setProperty("moquette.path","C:\\Users\\Administrator\\git\\investigate\\investigate-mqtt");

        Server server = new Server();
        server.startServer(properties);
    }

}
