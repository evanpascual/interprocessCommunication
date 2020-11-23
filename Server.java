import java.net.*;
import java.io.*;


public class Server {
    public static void main(String args[]) {

        // Create socket connection point
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);

            byte[] msgBuff = new byte[1024];
            // Wait for connections to proccess
            while (true) {
                // Create Packet to hold incoming packet Data
                DatagramPacket request = new DatagramPacket(msgBuff, msgBuff.length);
                aSocket.receive(request);               // Recieve Data packet from Sent Client

                // Create Packet to send in response back
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);                    // Send packet in response to client
            }
        } catch (SocketException e) { System.out.println("Socket: " + e.getMessage());  // Check for Socket bounds
        } catch (IOException e) { System.out.println("IO: " + e.getMessage());          // Check IO Bounds
        } finally { if (aSocket != null) aSocket.close();                               // If no connection
        }

    }
}
