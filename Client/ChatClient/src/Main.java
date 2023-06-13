import java.io.IOException;
import java.net.Socket;
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
    private AtomicReference<Socket> socketRef;
    private String host;
    private int port;

    public LivelinessAnalyserThread(AtomicReference<Boolean> isConnnectionToServerAlive,
            AtomicReference<Socket> socketRef, String host, int port) {
        this.isConnnectionToServerAlive = isConnnectionToServerAlive;
        this.socketRef = socketRef;
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server: " + host + " on port: " + port);
            socketRef.set(socket);
            isConnnectionToServerAlive.set(true);
        } catch (IOException e) {
            Logger.log("Some error occurred while establishing connection for the first time.",
                    "LivelinessAnalyserThread",
                    Logger.LogLevels.Error);
            e.printStackTrace();
        }
    }
}

public class Main {
    public static AtomicReference<Boolean> isConnnectionToServerAlive = new AtomicReference<Boolean>(false);
    public static AtomicReference<Socket> socketRef = new AtomicReference<Socket>(null);

    public static void main(String[] args) {
        LivelinessAnalyserThread livelinessAnalyserThread = new LivelinessAnalyserThread(
                isConnnectionToServerAlive, socketRef, "localhost", 3000);
        livelinessAnalyserThread.start();
    }
}
