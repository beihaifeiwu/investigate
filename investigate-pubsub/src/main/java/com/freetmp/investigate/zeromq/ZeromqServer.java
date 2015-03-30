package com.freetmp.investigate.zeromq;

import org.zeromq.ZMQ;

/**
 * Created by LiuPin on 2015/3/28.
 */
public class ZeromqServer {

    public static void main(String[] args) {

        // 创建一个包含一个I/O线程的context
        ZMQ.Context context = ZMQ.context(1);
        // 创建一个publisher类型的socket
        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        // 将当前publisher绑定到5555端口上，可以接受subscriber的订阅
        publisher.bind("tcp://*:5555");

        while (!Thread.currentThread().isInterrupted()){
            String message = "topic hello";
            publisher.send(message);
        }

        publisher.close();
        context.term();
    }
}
