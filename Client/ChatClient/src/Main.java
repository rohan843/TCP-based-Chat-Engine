import utils.Logger;
import utils.SelfData;
import java.util.concurrent.ConcurrentLinkedQueue;
import datastrs.MessagesStore;
import datastrs.SysData;
import threads.CLIManagerThread;
import threads.ReceiveQueueProcessorThread;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    static ConcurrentLinkedQueue<String> recvQueue = new ConcurrentLinkedQueue<String>();
    static ConcurrentLinkedQueue<String> sendQueue = new ConcurrentLinkedQueue<String>();
    static MessagesStore messages = new MessagesStore();
    static SysData sysData = new SysData();
    static ReceiveQueueProcessorThread receiveQueueProcessorThread;
    static CLIManagerThread cliManagerThread;
    static AtomicBoolean isSystemExitInitiated = new AtomicBoolean(false);
    static SelfData selfData;

    public static void main(String[] args) {
        Logger.log("System Initiating", "Main");

        // Initialize threads
        receiveQueueProcessorThread = new ReceiveQueueProcessorThread(recvQueue, sysData, messages,
                isSystemExitInitiated);
        cliManagerThread = new CLIManagerThread(messages, sysData, isSystemExitInitiated, sendQueue, selfData);

        // Start threads
        receiveQueueProcessorThread.start();
        cliManagerThread.start();

        // Join on threads
        try {
            cliManagerThread.join();
            receiveQueueProcessorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Logger.log("System terminating", "Main", Logger.ElevatedCriticalityLogLevels.Info);
    }
}
