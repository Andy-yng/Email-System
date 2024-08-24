/**
 * The <code>Mailbox</code> creates a mailbox that holds folders
 * @author Andy Yang
 *    email:andy.yang.2@stonybrook.edu
 *    SBU ID: 115104866
 */
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.HashMap;
import java.io.Serializable;

public class Mailbox implements Serializable{

    private Folder inbox = new Folder("Inbox");

    private Folder trash = new Folder("Trash");

    private ArrayList<Folder> folders = new ArrayList<Folder>();

    HashMap<String, Folder> hashFolder = new HashMap<>();

    /**
     * Constructor for Mailbox
     */

    public Mailbox(){
        this.folders = new ArrayList<>();
        folders.add(inbox);
        folders.add(trash);

    }

    /**
     * Setters
     */

    public void setInbox(Folder inbox){
        this.inbox = inbox;
    }

    public void setTrash(Folder trash){
        this.trash = trash;
    }

    public void setfolder(ArrayList<Folder> folders){
        this.folders = folders;
    }

    /**
     * Getters
     */

    public Folder getInbox(){
        return this.inbox;
    }

    public Folder getTrash(){
        return this.trash;
    }

    /**
     * Retrieves a folder
     * @param name name of folder
     * @return folder
     */

    public Folder getFolder(String name){
        for(int i=0; i<folders.size(); i++) {
            if(folders.get(i).getName().equals(name)){
                return folders.get(i);
            }
        }
        System.out.println("Folder does not exist.");
        return null;
    }

    /**
     * Adds a folder
     * @param folder folder to be added
     */

    public void addFolder(Folder folder){
        if (hashFolder.containsValue(folder)) {
            System.out.println("Folder already exists");
        }
        folders.add(folder);
        hashFolder.put(folder.getName(), folder);

    }

    /**
     * Deletes a folder
     * @param name name of folder to be deleted
     */

    public void deleteFolder(String name){
        if(name.equals("Inbox") || name.equals("Trash")){
            System.out.println(name + " cannot be deleted.");
            return;
        }
        if (!hashFolder.containsKey(name)) {
            System.out.println("Folder doesn't exist");
        }
        for(int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(name)) {
                folders.remove(i);
                System.out.println(name + " has been successfully removed.");
                return;
            }
        }
    }

    /**
     * Writes an email
     */
    public void composeEmail() {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Enter recipient (To): ");
        String to = stdin.nextLine();
        System.out.print("Enter carbon copy recipients (CC): ");
        String cc = stdin.nextLine();
        System.out.print("Enter blind carbon copy recipients (BCC): ");
        String bcc = stdin.nextLine();
        System.out.print("Enter subject line: ");
        String subject = stdin.nextLine();
        System.out.print("Enter body: ");
        String body = stdin.nextLine();
        Email newEmail = new Email(to, cc, bcc, subject, body);
        GregorianCalendar cal = new GregorianCalendar();
        newEmail.setTimestamp(cal);
        if (to == null || subject == null) {
            System.out.println("Either recipient and/or subject is null, try again");
        } else {
            inbox.addEmail(newEmail);
            System.out.println("Email successfully added to Inbox.");
        }
    }

    /**
     * Deletes an email
     * @param email
     */
    public void deleteEmail(Email email){
        trash.addEmail(email);
    }

    /**
     * clears the trash
     */

    public void clearTrash(){
        trash.getEmails().clear();
        System.out.println("Trash has been cleared");
    }

    /**
     * Moves an email to another folder
     * @param email email to be moved
     * @param target destination
     */
    public void moveEmail(Email email, Folder target) {
        target.addEmail(email);
    }

}
