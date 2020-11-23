import java.util.Scanner;
import java.io.*;
import java.net.*; 

public class Client{

    //prompts user to enter a valid ip address until one is entered. Returns the ip addresss as a string.  
    static String getIPAddress(){
        
        //initializers
        Scanner ip = new Scanner(System.in);
        String ipAddress = "";
        boolean valid = false;
        String ipChecker = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        //loop for user input of ip address
        while(!valid){
            System.out.println("Enter a valid IP Address:");
            ipAddress = ip.nextLine();
            valid = ipAddress.matches(ipChecker);
        }
        
        return ipAddress;
    }

    //prompts user to enter a valid port number until one is entered. 
    static int getPortNum(){

        //initializers
        int portNum;
        Scanner port = new Scanner(System.in);

        //user input for port number
        System.out.println("Enter a valid port number:");
        while(true) { //valid integer check
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

    public static void main(String[] args) throws UnknownHostException {

        //initialize ip and host
        InetAddress ipAddress = InetAddress.getByName(getIPAddress());
        int portNum = getPortNum();

        //Creating Socket
        try (
            Socket echoSocket = new Socket(ipAddress, portNum);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("UnknownHostException");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IOEXception");
            System.exit(1);
        } 
    }
}