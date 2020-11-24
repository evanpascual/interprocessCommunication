import java.net.*;
import java.io.*;

public class Server{

    public static void main(String args[]){
        System.out.println("Beginning echo server...");

        //Instantiate Server socket instance
        //used by server to listen to client request
        try(ServerSocket echoServer = new ServerSocket(8000)){
            Socket clientSocket = echoServer.accept();
            System.out.println("Connected to client!");
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true); 

            //loops until the message is null
            String message;
            while((message = bufferedReader.readLine()) != null){
                System.out.println("Server: " + message);
                //print back to client
                output.println(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}