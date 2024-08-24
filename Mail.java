/**
 * The <code>Mail</code> is where the main program runs
 * @author Andy Yang
 *    email:andy.yang.2@stonybrook.edu
 *    SBU ID: 115104866
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Mail implements Serializable{
    public static Folder cursor;
    public static Mailbox mailbox = new Mailbox();

    /**
     * submenu of the folder
     */

    public static void subMenu() {
        cursor.sortByDateDescending();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(cursor.getName());
            System.out.println("M – Move email\n" +
                    "\n" +
                    "D – Delete email\n" +
                    "\n" +
                    "V – View email contents\n" +
                    "\n" +
                    "SA – Sort by subject line in ascending order\n" +
                    "\n" +
                    "SD – Sort by subject line in descending order\n" +
                    "\n" +
                    "DA – Sort by date in ascending order\n" +
                    "\n" +
                    "DD – Sort by date in descending order\n" +
                    "\n" +
                    "R – Return to mailbox");
            String option = scanner.nextLine().toUpperCase();
            switch (option) {
                case "M": {
                    Email move;
                    System.out.print("Enter the index of the email to move: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index <= cursor.getEmails().size() - 1) {
                        move = cursor.getEmails().get(index);
                        System.out.print("Select a folder to move " + move.getSubject() + "to: ");
                        String target = scanner.nextLine();
                        mailbox.moveEmail(move, mailbox.getFolder(target));
                        cursor.removeEmail(index);
                    } else {
                        System.out.println("Invalid Index");
                    }
                    break;
                }
                case "D": {
                    Email deleteEmail;
                    System.out.print("Enter the index of the email to delete: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index <= cursor.getEmails().size() - 1) {
                        deleteEmail = cursor.getEmails().get(index);
                        cursor.removeEmail(index);
                        mailbox.deleteEmail(deleteEmail);
                        System.out.println("Email successfully deleted");
                    } else {
                        System.out.println("Invalid Index");
                    }
                    break;
                }
                case "V": {
                    System.out.print("Enter email index: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index <= cursor.getEmails().size() - 1) {
                        System.out.println("To: " + cursor.getEmails().get(index).getTo());
                        System.out.println("CC: " + cursor.getEmails().get(index).getCc());
                        System.out.println("BCC: " + cursor.getEmails().get(index).getBcc());
                        System.out.println("Subject: " + cursor.getEmails().get(index).getSubject());
                        System.out.println("To: " + cursor.getEmails().get(index).getBody());
                    } else {
                        System.out.println("Invalid Index");
                    }
                    break;
                }
                case "SA":
                    cursor.sortBySubjectAscending();
                    break;
                case "SD":
                    cursor.sortBySubjectDescending();
                    break;
                case "DA":
                    cursor.sortByDateAscending();
                    break;
                case "DD":
                    cursor.sortByDateDescending();
                    break;
                case "R":
                    return;
                default:
                    System.out.println("Enter a valid option.");
                    break;
            }

        }
    }

    /**
     * This is where the main simulation runs
     * @param args
     */

    public static void main(String[] args){
        while (true) {
            Scanner stdin = new Scanner(System.in);
            System.out.println("A – Add folder\n" +
                    "\n" +
                    "R – Remove folder\n" +
                    "\n" +
                    "C – Compose email\n" +
                    "\n" +
                    "F – Open folder\n" +
                    "\n" +
                    "I – Open Inbox\n" +
                    "\n" +
                    "T – Open Trash\n" +
                    "\n" +
                    "Q – Quit\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Enter a user option:");
            String option = stdin.nextLine().toUpperCase();
            switch(option){
                case "A":
                    System.out.print("Enter folder name: ");
                    String folderName = stdin.nextLine();
                    Folder newFolder = new Folder(folderName);
                    mailbox.addFolder(newFolder);
                    break;
                case "R":
                    System.out.print("Enter folder to remove: ");
                    String removeFolder = stdin.nextLine();
                    mailbox.deleteFolder(removeFolder);
                    break;
                case "C":
                    mailbox.composeEmail();
                    break;
                case "E":
                    mailbox.clearTrash();
                case "F":
                    System.out.print("Enter folder name: ");
                    String openFolder = stdin.nextLine();
                    cursor = mailbox.getFolder(openFolder);
                    if(cursor == null) {
                        throw new IllegalArgumentException("No such folder exists");
                    } else{
                        subMenu();
                    }
                    break;
                case "I":
                    cursor = mailbox.getFolder("Inbox");
                    subMenu();
                    break;
                case "T":
                    cursor = mailbox.getFolder("Trash");
                    subMenu();
                    break;
                case "Q":
                    System.out.println("Program successfully exited and mailbox saved.");
                    System.exit(0);
                default:
                        System.out.println("Select a valid option");
                    break;
            }
        }


    }
}