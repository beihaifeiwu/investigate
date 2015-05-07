package com.freetmp.investigate.akka.echoserver;

import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;

/**
 * Created by LiuPin on 2015/5/6.
 */
public class Handler extends UntypedActor {
  @Override public void onReceive(Object o) throws Exception {

    System.out.println("Handler received:" + o);
    if (o instanceof Tcp.Received) {
      final ByteString data = ((Tcp.Received) o).data();
      getSender().tell(TcpMessage.write(data), getSelf());
    } else if (o instanceof Tcp.ConnectionClosed) {
      getContext().stop(getSelf());
    }

  }
}
