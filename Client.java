import java.util.Scanner;

// 2. Sends the message to the server

// 3. Displays whatever the server sends back by using the same socket

// 4. Displays an error message if the IP address or port number were entered incorrectly

public class Client{
    public static void main(String[] args){

        //initialize scanners to take in user input
        Scanner ip = new Scanner(System.in);
        Scanner port = new Scanner(System.in);
        Scanner message = new Scanner(System.in);

        //initialize regex for input validation
        String ipChecker = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        //user input for ip address
        System.out.println("Enter the IP Address:");
        String ipAddress = ip.nextLine();
        while(ipAddress.matches(ipChecker) != true){ //input validation using regex
            System.out.println("Enter a valid IP Address:");
            ipAddress = ip.nextLine();
        }
        
        //user input for port number
        int portNum;
        System.out.println("Enter the port number of the server:");
        while(true) { //TODO: change this to also valid negative integers
            try {
                  portNum = Integer.parseInt(port.nextLine());
                  break;
            }catch (NumberFormatException e) {
                System.out.println("Enter valid port number: ");
                continue;
            }
        }

        //user input for message
        System.out.println("Enter the message to be sent to the server:");
        String messageToServer = message.nextLine();

        //CHECKS INPUT. DELETE LATER
        System.out.println("IP Address: " + ipAddress + "\nPort Number: " + portNum + "\nMessage: " + messageToServer);
        
        //close the scanners
        ip.close();
        port.close();
        message.close();
    }
}