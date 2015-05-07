package com.freetmp.investigate.akka.echoserver;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;

import java.net.InetSocketAddress;

/**
 * Created by LiuPin on 2015/5/6.
 */
public class Accepter extends UntypedActor {

  private final ActorRef tcpManager;

  public Accepter(ActorRef tcpManager) {
    this.tcpManager = tcpManager;
  }

  @Override public void onReceive(Object o) throws Exception {
    System.out.println("Accepter received:" + o);

    if (o instanceof Integer) {

      final int port = (Integer) o;
      final InetSocketAddress endpoint = new InetSocketAddress("localhost", port);
      final Object cmd = TcpMessage.bind(getSelf(), endpoint, 100);
      tcpManager.tell(cmd, getSelf());

    } else if (o instanceof Tcp.Bound) {

      tcpManager.tell(o, getSelf());

    } else if (o instanceof Tcp.CommandFailed) {

      getContext().stop(getSelf());

    } else if (o instanceof Tcp.Connected) {

      final Tcp.Connected connected = (Tcp.Connected) o;
      tcpManager.tell(connected, getSelf());
      final ActorRef handler = getContext().actorOf(Props.create(Handler.class));
      getSender().tell(TcpMessage.register(handler), getSelf());

    }
  }
}
