package datastrs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Collections;
import utils.ChatMessage;
import utils.Message;

public class MessagesStore {
    // Username --> ChatMessage linked list (sorted on the basis of timestamp,
    // earliest first)
    private ConcurrentHashMap<String, SortedMap<String, ChatMessage>> map = new ConcurrentHashMap<String, SortedMap<String, ChatMessage>>();

    // Update the message store with new values.
    public void update(Message message) {
        String senderUsername = message.getSenderUsername();
        String receiverUsername = message.getReceiverUsername();
        String messageContent = message.getMessageContent();
        String timestamp = message.getMessageTimestamp();
        boolean wasMessageSentToCurrentClient = !message.isOutboundMessage();

        ChatMessage chatMessage = new ChatMessage(senderUsername, receiverUsername, timestamp,
                wasMessageSentToCurrentClient, messageContent);

        // The person with whom the current user is chatting
        String conversationUsername = senderUsername;
        if (!wasMessageSentToCurrentClient) {
            conversationUsername = receiverUsername;
        }

        if (!map.containsKey(conversationUsername)) {
            map.put(conversationUsername, Collections.synchronizedSortedMap(new TreeMap<String, ChatMessage>()));
        }

        map.get(conversationUsername).put(timestamp, chatMessage);
    }
}
