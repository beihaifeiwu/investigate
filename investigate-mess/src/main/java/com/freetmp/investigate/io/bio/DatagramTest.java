package com.freetmp.investigate.io.bio;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by LiuPin on 2015/12/15.
 */
public class DatagramTest {
  public static void main(String[] args) throws Exception {
    DatagramSocket socket = new DatagramSocket(30014);

    if(args.length > 0){
      System.out.println("Server started!");
      while (true){
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
        socket.receive(packet);
        System.out.println("Server received from " + packet.getSocketAddress() + ": " + new String(bytes, 0, packet.getLength(), "UTF-8"));
        DatagramPacket response = new DatagramPacket(bytes, 0, packet.getLength(), packet.getSocketAddress());
        socket.send(response);
      }
    } else {
      System.out.println("Client started!");
      Scanner scanner = new Scanner(System.in);
      SocketAddress socketAddress = new InetSocketAddress("localhost", 61111);
      while (scanner.hasNext()){
        byte[] bytes = scanner.next().getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, socketAddress);
        socket.send(packet);

        bytes = new byte[1024];
        packet = new DatagramPacket(bytes, 0, bytes.length);
        socket.receive(packet);
        System.out.println("Client received from + " + packet.getSocketAddress() + ": " + new String(bytes, 0, packet.getLength(), "UTF-8"));
      }
    }

  }
}
