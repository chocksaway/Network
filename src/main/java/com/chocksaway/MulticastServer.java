package com.chocksaway;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class MulticastServer {
    public static void main(String args[]) throws UnknownHostException {
        System.out.println("Multicast  Time Server");
        while (true) {
            String dateText = new Date().toString();
            byte[] buffer = new byte[256];
            buffer = dateText.getBytes();

            InetAddress group = InetAddress.getByName("224.0.0.0");
            DatagramPacket packet;
            packet = new DatagramPacket(buffer, buffer.length,
                    group, 8888);
            try (DatagramSocket serverSocket = new DatagramSocket()) {

                serverSocket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Time sent: " + dateText);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                // Handle exception
            }
        }
    }
}
