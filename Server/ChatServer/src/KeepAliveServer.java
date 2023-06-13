import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class KeepAliveServer {

    public static void takeUserInput() {
        System.out.println("Press ENTER");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
    }

    public static void main(String[] args) {
        try {
            // Create a server socket to listen on port 3000
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server listening on port 3000...");

            try {
                while (true) {
                    // Accept incoming connections
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Connected to client: " + clientSocket.getInetAddress());

                    // Set keep-alive option for the socket
                    clientSocket.setKeepAlive(true);

                    // Handle the connection (you can add your custom logic here)
                    takeUserInput();

                    // Close the client socket
                    clientSocket.close();
                    System.out.println("Connection closed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
