import utils.Logger;
import java.util.concurrent.ConcurrentLinkedQueue;
import datastrs.MessagesStore;
import datastrs.SysData;
import threads.ReceiveQueueProcessorThread;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    static ConcurrentLinkedQueue<String> recvQueue = new ConcurrentLinkedQueue<String>();
    static ConcurrentLinkedQueue<String> sendQueue = new ConcurrentLinkedQueue<String>();
    static MessagesStore messages = new MessagesStore();
    static SysData sysData = new SysData();
    static ReceiveQueueProcessorThread receiveQueueProcessorThread;
    static AtomicBoolean isSystemExitInitiated = new AtomicBoolean(false);
    
    public static void main(String[] args) {
        Logger.log("System Initiating", "Main");
        receiveQueueProcessorThread = new ReceiveQueueProcessorThread(recvQueue, sysData, messages, isSystemExitInitiated);
        receiveQueueProcessorThread.start();

    }
}
