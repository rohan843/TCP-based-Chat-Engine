package threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import datastrs.MessagesStore;
import datastrs.SysData;
import utils.Logger;
import utils.Message;

public class ReceiveQueueProcessorThread extends Thread {
    // The queue to take received data from.
    ConcurrentLinkedQueue<String> recvQueue;

    // The data structures to place received data in.
    SysData sysData;
    MessagesStore messages;

    // Monitoring system exit sequence.
    AtomicBoolean isSystemExitInitiated;

    public ReceiveQueueProcessorThread(ConcurrentLinkedQueue<String> recvQueue, SysData sysData, MessagesStore messages,
            AtomicBoolean isSystemExitInitiated) {
        this.recvQueue = recvQueue;
        this.sysData = sysData;
        this.messages = messages;
        this.isSystemExitInitiated = isSystemExitInitiated;
    }

    @Override
    public void run() {
        while (!isSystemExitInitiated.get()) {
            if (!recvQueue.isEmpty()) {
                // Retrieve the JSON message string from recvQueue.
                String rawMessageString = recvQueue.remove();

                // Parse the message string.
                Message message = new Message(rawMessageString);

                // Place the message data in appropriate data structure.
                if (message.isServiceMessage()) {
                    sysData.update(message);
                } else {
                    messages.update(message);
                }
            }
        }
        Logger.log("Exiting", "ReceiveQueueProcessorThread", Logger.ElevatedCriticalityLogLevels.Info);
    }
}
