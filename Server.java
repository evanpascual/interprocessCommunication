import java.net.*;
import java.io.*;


public class Server {
    public static void main(String args[]) {

        DatagramSocket aSocket = null;

        try {
            aSocket = new DatagramSocket(6789);
            byte[] msgBuff = new byte[1024];
            while (true) {
                DatagramPacket request = new DatagramPacket(msgBuff, msgBuff.length);
                aSocket.receive(request);               // Recieve Data packet from Sent Client

                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) aSocket.close();
        }

    }
}
