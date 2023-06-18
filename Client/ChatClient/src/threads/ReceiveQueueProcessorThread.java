package threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import datastrs.MessagesStore;
import datastrs.SysData;
import utils.Message;


public class ReceiveQueueProcessorThread extends Thread {
    // The queue to take received data from.
    ConcurrentLinkedQueue<String> recvQueue;

    // The data structures to place received data in.
    SysData sysData;
    MessagesStore messages;

    public ReceiveQueueProcessorThread(ConcurrentLinkedQueue<String> recvQueue, SysData sysData, MessagesStore messages) {
        this.recvQueue = recvQueue;
        this.sysData = sysData;
        this.messages = messages;
    }

    @Override
    public void run() {
        while(true) {
            if (!recvQueue.isEmpty()) {
                // Retrieve the JSON message string from recvQueue.
                String rawMessageString = recvQueue.remove();

                // Parse the message string.
                Message message = new Message(rawMessageString);

                // Place the message data in appropriate data structure.
                if (message.isSystemMessage) {
                    sysData.update(message);
                } else {
                    messages.update(message);
                }
            }
        }
    }
}
