import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

class Logger {
    public enum LogLevels {
        Info,
        Warning,
        Error
    }

    public static void log(String message, String sender, LogLevels level) {
        String colorCode = "";
        String resetColorCode = "\u001B[0m";
        switch (level) {
            case Info:
                colorCode = "\u001B[32m";
                break;

            case Warning:
                colorCode = "\u001B[33m";
                break;

            case Error:
                colorCode = "\u001B[31m";
                break;

            default:

                break;
        }
        System.out.println(colorCode + sender + ": " + message + resetColorCode);
    }
}

class LivelinessAnalyserThread extends Thread {
    private AtomicReference<Boolean> isConnnectionToServerAlive;

    public LivelinessAnalyserThread(AtomicReference<Boolean> isConnnectionToServerAlive) {
        this.isConnnectionToServerAlive = isConnnectionToServerAlive;
    }

    public void run() {

        String host = "localhost";
        int port = 10002;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server: " + host + " on port: " + port);
            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!isConnnectionToServerAlive.get()) {
            try {
                Logger.log("Connection not established... retrying in 10s", "LivelinessAnalyserThread",
                        Logger.LogLevels.Info);
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static AtomicReference<Boolean> isConnnectionToServerAlive = new AtomicReference<Boolean>(false);

    public static void main(String[] args) {
        
    }
}
