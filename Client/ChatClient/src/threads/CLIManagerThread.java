package threads;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import datastrs.MessagesStore;
import datastrs.SysData;

public class CLIManagerThread extends Thread {
    // Data stores.
    MessagesStore messages;
    SysData sysData;

    // Monitoring system exit sequence.
    AtomicBoolean isSystemExitInitiated;

    // The send queue.
    ConcurrentLinkedQueue<String> sendQueue;

    public CLIManagerThread(MessagesStore messages, SysData sysData, AtomicBoolean isSystemExitInitiated, ConcurrentLinkedQueue<String> sendQueue) {
        this.messages = messages;
        this.sysData = sysData;
        this.isSystemExitInitiated = isSystemExitInitiated;
        this.sendQueue = sendQueue;
    }

    private void showAllUserStatuses() {
        System.out.println("All user statuses shown.");
    }

    private void showUserConversation(String username) {
        System.out.println("Conversation history of " + username + " shown.");
    }

    private void sendMessage(String username, String message) {
        System.out.println("Sent message: " + message + " to user: " + username);
    }

    @Override
    public void run() {
        while (!isSystemExitInitiated.get()) {
            // Display options
            System.out.println("Please choose an option:");
            System.out.println("\t0: Show all user's statuses");
            System.out.println("\t1: Show a user's conversations");
            System.out.println("\t2: Send a user a message");
            System.out.println("\t3: Exit");

            // Input choice
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            String username = null;
            String message = null;

            switch (choice) {
                case 0:
                    showAllUserStatuses();
                    break;
                case 1:
                    System.out.println("Please enter the target username:");
                    username = sc.nextLine();
                    showUserConversation(username);
                    break;
                case 2:
                    System.out.println("Please enter the target username:");
                    username = sc.nextLine();
                    System.out.println("Please enter the message in a single line:");
                    message = sc.nextLine();
                    sendMessage(username, message);
                    break;
                case 3:
                    System.out.println("Bye!");
                    isSystemExitInitiated.set(true);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

            sc.close();
        }
    }
}
