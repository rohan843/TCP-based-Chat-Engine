package threads;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import datastrs.MessagesStore;
import datastrs.SysData;
import datastrs.UserList;
import utils.DateTime;
import utils.Logger;
import utils.Message;
import utils.SelfData;
import java.util.ArrayList;

public class CLIManagerThread extends Thread {
    // Data stores.
    MessagesStore messages;
    SysData sysData;

    // Monitoring system exit sequence.
    AtomicBoolean isSystemExitInitiated;

    // The send queue.
    ConcurrentLinkedQueue<String> sendQueue;

    // Metadata about the user.
    SelfData selfData;

    public CLIManagerThread(MessagesStore messages, SysData sysData, AtomicBoolean isSystemExitInitiated, ConcurrentLinkedQueue<String> sendQueue, SelfData selfData) {
        this.messages = messages;
        this.sysData = sysData;
        this.isSystemExitInitiated = isSystemExitInitiated;
        this.sendQueue = sendQueue;
        this.selfData = selfData;
    }

    // Shows the status of all users.
    private void showAllUserStatuses() {
        UserList usernames = sysData.getUserList();
        int maxKeyLength = 0;
        for (String s: usernames.getList()) {
            maxKeyLength = Math.max(maxKeyLength, s.length());
        }
        for (String s: usernames.getList()) {
            System.out.printf("%-" + (maxKeyLength + 1) + "s %s%n", s + ":", sysData.getStatus(s).getStatusDisplayString());
        }
    }

    // Shows the conversations done with a user.
    private void showUserConversation(String username) {
        ArrayList<String> conversation = messages.getChatDisplayStringsFor(username);
        for(String conversatioString: conversation) {
            System.out.println(conversatioString);
        }
    }

    // Sends a specified message with the recepient set as the specified username by adding it to the send queue.
    private void sendMessage(String username, String messageText) {
        String timestamp = DateTime.getCurrentISTTimeStampString();
        Message message = new Message(selfData.getUsername(), username, messageText, timestamp, true, false);
        sendQueue.add(message.toJSONString());
        System.out.println("Message sent!");
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String selfUsername = sc.nextLine();
        selfData.setUsername(selfUsername);

        while (!isSystemExitInitiated.get()) {
            // Display options
            System.out.println("Please choose an option:");
            System.out.println("\t0: Show all user's statuses");
            System.out.println("\t1: Show a user's conversations");
            System.out.println("\t2: Send a user a message");
            System.out.println("\t3: Exit");

            // Input choice
            int choice = Integer.parseInt(sc.nextLine());

            // Temporary vars
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
        }
        sc.close();
        Logger.log("Exiting", "CLIManagerThread", Logger.ElevatedCriticalityLogLevels.Info);
    }
}
