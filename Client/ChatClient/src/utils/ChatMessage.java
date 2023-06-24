package utils;

public class ChatMessage {
    private String senderUsername;  // Who sent the message?
    private String recevierUsername;  // To whom the message was sent?
    private String timestamp;  // When was the message sent?
    private boolean wasMessageSentToCurrentClient;  // Was the receiver the client? (If false, the client sent the message)
    private String messageContent;  // What was the message?

    // Useful colors
    String grayColor = "\u001B[38;5;242m";
    String whiteColor = "\u001B[37m";
    String resetColor = "\u001B[0m";

    public ChatMessage(String senderUsername, String recevierUsername, String timestamp, boolean wasMessageSentToCurrentClient, String messageContent) {
        this.senderUsername = senderUsername;
        this.recevierUsername = recevierUsername;
        this.timestamp = timestamp;
        this.wasMessageSentToCurrentClient = wasMessageSentToCurrentClient;
        this.messageContent = messageContent;
    }

    public String toReadableString() {
        if (wasMessageSentToCurrentClient) {
            return senderUsername + " [" + timestamp + "]: " + whiteColor + messageContent + resetColor;
        } else {
            return "You [" + timestamp + "]: " + whiteColor + messageContent + resetColor;
        }
    }
}
