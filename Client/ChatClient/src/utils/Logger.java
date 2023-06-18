package utils;

public class Logger {
    public enum ElevatedCriticalityLogLevels {
        Unelevated,
        Info,
        Warning,
        Error
    }

    public static void log(String message, String sender, ElevatedCriticalityLogLevels level) {
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

    public static void log(String message, String sender) {
        System.out.println(sender + ": " + message);
    }
}
