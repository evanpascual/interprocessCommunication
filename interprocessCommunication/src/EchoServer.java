import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        System.out.println("Begin echo server..."); //test that the program has started
     
        int socket = EchoClient.getPortNum();
        while(socket == -1){
            socket = EchoClient.getPortNum();
        }
        try(ServerSocket echoServer = new ServerSocket(socket)){ //creates the socket
            Socket clientSocket = echoServer.accept();//listens for a connection to be made to this socket and accepts it.
            System.out.println("Connected to client");

            //get input string
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //get output string
            PrintWriter outputString = new PrintWriter(clientSocket.getOutputStream(), true);
            
            //loops through client messages until null
            String message;
            while((message = bufferedReader.readLine()) != null){
                System.out.println("Server: " + message); //prints on the server side
                outputString.println(message);  //prints to the client
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}