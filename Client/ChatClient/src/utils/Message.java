package utils;

// TODO: Properly add and obtain data members.
public class Message {
    public boolean isServiceMessage;
    private String senderUsername;
    private String receiverUsername;
    private String messageContentString;
    private String messageTimestamp;
    private boolean isOutboundMessage;
    
    // To convert a received JSON into a Message object.
    public Message(String rawMessageString) {

    }

    // To initialize a message object with specified data.
    public Message(String username, String message) {

    }

    // Converts the message object to its JSON equivalent.
    public String toJSONString() {
        return "";
    }

    // Returns the username of the sender.
    public String getSenderUsername() {
        return "";
    }

    // Returns the username of the receiver.
    public String getReceiverUsername() {
        return "";
    }

    // Returns the content of the message.
    public String getMessageContent() {
        return "";
    }

    // Returns the timestamp associated with the message.
    public String getMessageTimestamp() {
        return "";
    }

    // Returns true of the message is outbound.
    public boolean isOutboundMessage() {
        return false;
    }
}
