import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 10002;

        try {
            // Create a socket connection to the server
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server: " + host + " on port: " + port);

            // Perform any communication or data exchange with the server
            // ...

            // Close the socket connection
            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

