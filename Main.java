package message;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        ArrayList<Message> sentMessages = new ArrayList<>();

        // ----- PART 1: REGISTRATION -----
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String username = JOptionPane.showInputDialog("Enter Username (must contain _ and max 5 chars):");
        String password = JOptionPane.showInputDialog("Enter Password (8 chars, Capital, Number, Special):");
        String cellphone = JOptionPane.showInputDialog("Enter Cellphone e.g +27821234567:");

        String regResult = login.registerUser(username, password, firstName, lastName, cellphone);
        JOptionPane.showMessageDialog(null, regResult);
        if (!regResult.contains("successfully")) return;

        // ----- PART 1: LOGIN -----
        String loginUser = JOptionPane.showInputDialog("LOGIN - Username:");
        String loginPass = JOptionPane.showInputDialog("LOGIN - Password:");
        String loginMsg = login.returnLoginStatus(loginUser, loginPass);
        JOptionPane.showMessageDialog(null, loginMsg);
        if (!loginMsg.contains("Welcome")) return;

        // ----- PART 2: QUICKCHAT -----
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        while (true) {
            String menu = JOptionPane.showInputDialog("Choose:\n1) Send Messages\n2) Show recently sent messages (Coming Soon)\n3) Quit");
            if (menu == null || menu.equals("3")) break;

            if (menu.equals("1")) {
                String numStr = JOptionPane.showInputDialog("How many messages do you wish to send?");
                int numMessages = Integer.parseInt(numStr);

                for (int i = 0; i < numMessages; i++) {
                    String recipient = JOptionPane.showInputDialog("Enter Recipient Cell (+27...):");
                    String text = JOptionPane.showInputDialog("Enter Message (max 250 chars):");

                    if (text.length() > 250) {
                        JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters - you exceeded by " + (text.length() - 250));
                        i--; continue;
                    }

                    Message msg = new Message(i, recipient, text);

                 String cellCheck = msg.checkRecipientCell();
if (cellCheck.contains("incorrectly")) {
    JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    i--; continue;
}

                    String result = msg.sendMessageOption();
                    JOptionPane.showMessageDialog(null, result + "\n\n" + msg.printMessage());
                    sentMessages.add(msg);
                }
                JOptionPane.showMessageDialog(null, "Total messages sent: " + sentMessages.size());
            }
        }
    }
}