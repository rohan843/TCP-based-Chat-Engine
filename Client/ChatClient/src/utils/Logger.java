package utils;

public class Logger {
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
