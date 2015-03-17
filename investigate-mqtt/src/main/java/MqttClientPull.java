import org.fusesource.mqtt.client.*;

/**
 * Created by LiuPin on 2015/3/17.
 */
public class MqttClientPull {
    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost",1883);
        mqtt.setUserName("testuser");
        mqtt.setPassword("passwd");
        mqtt.setWillQos(QoS.AT_MOST_ONCE);

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        Topic[] topics = {new Topic("foo",QoS.AT_MOST_ONCE)};
        byte[] qoses = connection.subscribe(topics);

        while(true){
            Message message = connection.receive();
            System.out.print(message.getTopic());
            System.out.println(" : " + new String(message.getPayload()));
            message.ack();
        }

    }
}
