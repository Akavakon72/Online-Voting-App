package com.bt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Development-21
 */
public class VotingClient {

    static Socket client;
    private static int port = 6606;
    private static String ip = "192.168.1.42";

    public static void main(String[] args) {

        try {
            client = new Socket(ip, port);
            try (DataOutputStream dos = new DataOutputStream(
                    client.getOutputStream())) {
                dos.writeUTF("LOGIN");
                dos.writeUTF("as");
                dos.writeUTF("as");
                dos.flush();
            }
        } catch (IOException e1) {
            System.out.println('\n'
                    + "Message sending fail:Network Error" + e1);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        try {
            System.out.println("aya");
            client = new Socket(ip, port);
            System.out.println("accept hoya");
            DataInputStream dis = new DataInputStream(client.getInputStream());
            System.out.println("input bana");
            String string = dis.readUTF();

            System.out.println("Server:" + string);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("Message sending fail:Network Error" + e1);

        } finally {
            try {
                client.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}
