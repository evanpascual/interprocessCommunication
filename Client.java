import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client{

    static int portNum;

    // prompts user to enter a valid ip address until one is entered. Returns the ip
    // addresss as a string.
    static String setIPAddress() {

        // initializers
        Scanner ip = new Scanner(System.in);
        String ipAddress = "";
        boolean valid = false;
        String ipChecker = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        // loop for user input of ip address
        while (!valid) {
            System.out.println("Enter a valid IP Address:");
            ipAddress = ip.nextLine();
            valid = ipAddress.matches(ipChecker);
        }

        return ipAddress;
    }

    // prompts user to enter a valid port number until one is entered.
    static int setPortNum() {

        // initializers
        Scanner port = new Scanner(System.in);

        // user input for port number
        System.out.println("Enter a valid port number:");
        while (true) { // valid integer check
            try {
                portNum = port.nextInt();
                  while(portNum > 65535 || portNum < 1){ //valid integer check within range
                    System.out.println("Enter valid port number:");
                    portNum = port.nextInt();
                  }
                  break;
            }catch (NumberFormatException e) { //throse 
                System.out.println("Enter valid port number:");
                continue;
            }
        }

        return portNum;
    }


    public static void main(String args[]){
        System.out.println("Beginning echo client...");

        try{
            InetAddress inputAddress = InetAddress.getByName(setIPAddress()); //converts string to InetAddress
            int portNum = setPortNum();

            Socket socket = new Socket(inputAddress, 8000);     //creates socket on port number 8000

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Connected to server!");
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.print("Enter a message (to exit, enter 'exit'):");
                String message = scanner.nextLine();
                if("exit".equalsIgnoreCase(message)){   //exits on message "exit"
                    break;
                }
                out.println(message);
                String response = BufferedReader.readLine();
                System.out.println("Server response: " + response);
            }
        }catch(Exception e){

        }

    }
}