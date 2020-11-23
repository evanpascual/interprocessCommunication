import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main (String args[]){

        //initialize scanners to take in user input
        Scanner ip = new Scanner(System.in);
        Scanner port = new Scanner(System.in);
        Scanner message = new Scanner(System.in);
        Scanner yn = new Scanner(System.in);

        //initialize regex for input validation
        String ipChecker = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        //user input for ip address
        System.out.println("Enter the IP Address:");
        String ipAddress = ip.nextLine();
        while(ipAddress.matches(ipChecker) != true){                //input validation using regex
            System.out.println("Enter a valid IP Address:");
            ipAddress = ip.nextLine();
        }

        //user input for port number
        int portNum;
        System.out.println("Enter the port number of the server:");
        while(true) { //valid integer check
            try {
                portNum = Integer.parseInt(port.nextLine());
                while(portNum > 65535 || portNum < 1){ //valid integer check within range
                    System.out.println("Enter valid port number: ");
                    portNum = Integer.parseInt(port.nextLine());
                }
                break;
            }catch (NumberFormatException e) {
                System.out.println("Enter valid port number: ");
                continue;
            }
        }

        //user input for message
        System.out.println("Enter the message to be sent to the server:");
        String msgToServer = message.nextLine();

        //CHECKS INPUT. DELETE LATER
        System.out.println("IP Address: " + ipAddress + "\nPort Number: " + portNum + "\nMessage: " + msgToServer);


        System.out.println("Saved Msg: " + msgToServer);
        System.out.println("Msg Length: " + msgToServer.length());
        byte[]msg1 = msgToServer.getBytes();
        System.out.println("Msg Byte: " + msg1);

        DatagramSocket aSocket = null;                              // Initiate new Socket
        try {
            aSocket = new DatagramSocket();                         // Create a new Socket
            byte[]msg = msgToServer.getBytes();                     // Message in Byte format
            InetAddress aHost = InetAddress.getLocalHost();         // Create Local Domain
          //  InetAddress aHost = InetAddress.getByName(args[1]);   Original

            DatagramPacket request =new DatagramPacket(msg, msg.length,aHost,portNum);
            aSocket.send(request);

            byte[]msgBuff = new byte[1024];
            DatagramPacket reply = new DatagramPacket(msgBuff, msgBuff.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
        }catch(SocketException e) { System.out.println("Socket: " + e.getMessage());
        }catch(IOException e) { System.out.println("IO: " + e.getMessage());
        }finally { if(aSocket !=null) aSocket.close();}


        //close the scanners
        ip.close();
        port.close();
        message.close();
        yn.close();
    }
}
