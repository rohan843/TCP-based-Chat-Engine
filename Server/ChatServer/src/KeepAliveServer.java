import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KeepAliveServer {
    public static void main(String[] args) {
        try {
            // Create a server socket to listen on port 10002
            ServerSocket serverSocket = new ServerSocket(10002);
            System.out.println("Server listening on port 10002...");

            while (true) {
                // Accept incoming connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client: " + clientSocket.getInetAddress());

                // Set keep-alive option for the socket
                clientSocket.setKeepAlive(true);

                // Handle the connection (you can add your custom logic here)
                // ...

                // Close the client socket
                clientSocket.close();
                System.out.println("Connection closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
