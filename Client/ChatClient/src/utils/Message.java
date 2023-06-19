package utils;


public class Message {
    private boolean isServiceMessage;
    private String senderUsername;
    private String receiverUsername;
    private String messageContentString;
    private String messageTimestamp;
    private boolean isOutboundMessage;

    // TODO: To convert a received JSON into a Message object (extract values of data members).
    public Message(String rawMessageString) {

    }

    // To initialize a message object with specified data.
    public Message(String senderUsername, String receiverUsername, String messageContentString, String messageTimestamp,
            boolean isOutboundMessage, boolean isServiceMessage) {
        this.isServiceMessage = isServiceMessage;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.messageContentString = messageContentString;
        this.messageTimestamp = messageTimestamp;
        this.isOutboundMessage = isOutboundMessage;
    }

    // Converts the message object to its JSON equivalent.
    public String toJSONString() {
        return "";
    }

    // Returns the username of the sender.
    public String getSenderUsername() {
        return senderUsername;
    }

    // Returns the username of the receiver.
    public String getReceiverUsername() {
        return receiverUsername;
    }

    // Returns the content of the message.
    public String getMessageContent() {
        return messageContentString;
    }

    // Returns the timestamp associated with the message.
    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    // Returns true if the message is outbound.
    public boolean isOutboundMessage() {
        return isOutboundMessage;
    }

    // Returns true if the message is a service message.
    public boolean isServiceMessage() {
        return isServiceMessage;
    }
}
