package com.freetmp.investigate.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiuPin on 2016/1/25.
 */
public class SocketPortTest {

  static ExecutorService service = Executors.newFixedThreadPool(2);

  public static void main(String[] args) throws IOException, InterruptedException {

    service.execute(() -> {
      try {
        ServerSocket socket = new ServerSocket(10000);
        System.out.println("TCP server[" + socket + "] listen on 10000");

        while (!socket.isClosed()){
          Socket accept = socket.accept();
          DataInputStream dis = new DataInputStream(accept.getInputStream());
          while (!accept.isClosed()){
            byte[] bytes = new byte[128];
            int length = dis.read(bytes);
            System.out.println(new String(bytes, 0, length));
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    });


    service.execute(() -> {
      try {
        DatagramSocket datagramSocket = new DatagramSocket(10000);
        System.out.println("UDP server[" + datagramSocket + "] listen on 10000");

        while (!datagramSocket.isClosed()) {
          byte[] bytes = new byte[1024];
          DatagramPacket packet = new DatagramPacket(bytes, 0, 1024);
          datagramSocket.receive(packet);
          System.out.println("UDP: " + new String(packet.getData(), packet.getOffset(), packet.getLength()));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    service.awaitTermination(200, TimeUnit.SECONDS);

  }
}
