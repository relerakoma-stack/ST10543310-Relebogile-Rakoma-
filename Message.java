package message;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    // For tests - must be public static
    public static int totalMessages = 0;
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messages = new ArrayList<>();

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id).substring(0, 10);
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public String checkRecipientCell() {
    if (recipient == null) {
        return "Cell phone number incorrectly formatted or does not contain international code.";
    }
    if (recipient.matches("^\\+27[0-9]{9}$")) {
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number incorrectly formatted or does not contain international code.";
    }
}

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words.length > 0? words[0] : "";
        String last = words.length > 1? words[words.length - 1] : first;
        String idStart = messageID.substring(0, 2);
        return (idStart + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public String sentMessage(int option) {
        // This method is used by tests
        if (option == 1) {
            totalMessages++;
            sentMessages.add(this);
            messageIDs.add(this.messageID);
            messageHashes.add(this.messageHash);
            messages.add(this.messageText);
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete message.";
        } else {
            storeMessage();
            return "Message successfully stored.";
        }
    }

    public String sendMessageOption() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null,
            "Choose what to do:\n" + messageText,
            "Send Message", 0, 3, null, options, options[0]);

        if (choice == 0) return sentMessage(1);
        if (choice == 1) return sentMessage(2);
        if (choice == 2) return sentMessage(3);
        return "Message disregarded.";
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("storedMessages.json", true);
            writer.write("{\"messageID\":\"" + messageID + "\",\"hash\":\"" + messageHash + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + messageText + "\"}\n");
            writer.close();
        } catch (IOException e) {
            // ignore for test
        }
    }

    public String printMessage() {
        return "MessageID: " + messageID + "\nHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}