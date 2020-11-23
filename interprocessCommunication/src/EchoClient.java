import java.util.Scanner;
import java.io.*;
import java.net.*; 

public class EchoClient{

    public static int portNum = -1; //creating global variables to send to echo server  

    //prompts user to enter a valid ip address until one is entered. Returns the ip addresss as a string.  
    static String setIPAddress(){
        
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
    static int setPortNum(){

        //initializers
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

    //used to send to echo server
    static int getPortNum(){
        return portNum;
    }

    public static void main(String[] args){

        System.out.println("Begin echo client..."); //test that the program has started

        try{
            InetAddress ipAddress = InetAddress.getByName(setIPAddress());
            int portNum = setPortNum();
            Socket socket = new Socket(ipAddress, portNum);// creating Socket
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server!");
            
            Scanner scanner = new Scanner(System.in); //takes user input
            while(true){
                System.out.println("Enter a message to echo:");
                String message = scanner.nextLine();
                if("exit".equalsIgnoreCase(message)){
                    break;
                }
                out.println(message);
                String response = bufferedReader.readLine();
                System.out.println("Server: ");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}