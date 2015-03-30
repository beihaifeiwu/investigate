package com.freetmp.investigate.zeromq;

import org.zeromq.ZMQ;

/**
 * Created by LiuPin on 2015/3/28.
 */
public class ZeromqClient {

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            final int out = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ZMQ.Context context = ZMQ.context(1);
                    ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
                    subscriber.connect("tcp://127.0.0.1:5555");
                    subscriber.subscribe("topic".getBytes());

                    for(int j = 0; j < 100; j++){
                        byte[] message = subscriber.recv();
                        System.out.println("receive "+ out +"-" +j+" : " + new String(message));
                    }

                    subscriber.close();
                    context.term();
                }
            }).start();
        }
    }
}
