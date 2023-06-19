package datastrs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import utils.ChatMessage;
import utils.Message;

public class MessagesStore {
    // Username --> ChatMessage linked list (sorted on the basis of timestamp, earliest first)
    ConcurrentHashMap<String, ConcurrentLinkedDeque<ChatMessage>> map = new ConcurrentHashMap<String, ConcurrentLinkedDeque<ChatMessage>>();
    // Update the message store with new values.
    public void update(Message message) {
        String senderUsername = message.getSenderUsername();
        String receiverUsername = message.getReceiverUsername();
        String messageContent = message.getMessageContent();
        String timestamp = message.getMessageTimestamp();
        boolean wasMessageSentToCurrentClient = !message.isOutboundMessage();
    }
}
