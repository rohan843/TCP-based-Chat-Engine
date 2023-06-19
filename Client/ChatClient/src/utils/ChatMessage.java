package utils;

public class ChatMessage {
    private String senderUsername;  // Who sent the message?
    private String recevierUsername;  // To whom the message was sent?
    private String timestamp;  // When was the message sent?
    private boolean wasMessageSentToCurrentClient;  // Was the receiver the client? (If false, the client sent the message)
    private String messageContent;  // What was the message?

    public ChatMessage(String senderUsername, String recevierUsername, String timestamp, boolean wasMessageSentToCurrentClient, String messageContent) {
        this.senderUsername = senderUsername;
        this.recevierUsername = recevierUsername;
        this.timestamp = timestamp;
        this.wasMessageSentToCurrentClient = wasMessageSentToCurrentClient;
        this.messageContent = messageContent;
    }


}
