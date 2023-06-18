import utils.Logger;
import java.util.concurrent.ConcurrentLinkedQueue;
import datastrs.MessagesStore;
import datastrs.SysData;

public class Main {
    ConcurrentLinkedQueue<String> recvQueue = new ConcurrentLinkedQueue<String>();
    ConcurrentLinkedQueue<String> sendQueue = new ConcurrentLinkedQueue<String>();
    MessagesStore messages = new MessagesStore();
    SysData sysData = new SysData();
    
    public static void main(String[] args) {
        Logger.log("System Initiating", "Main");
        
    }
}
