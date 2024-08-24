/**
 * The <code>Folder</code> creates folders that hold emails
 * @author Andy Yang
 *    email:andy.yang.2@stonybrook.edu
 *    SBU ID: 115104866
 */
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;

public class Folder implements Serializable{
    private ArrayList<Email> emails;
    private String name;
    private String currentSortingMethod;

    /**
     * Constructor for Folder
     * @param name name of the folder
     */
    public Folder(String name){
        this.emails = new ArrayList<Email>();
        this.name = name;
        this.currentSortingMethod = "DD";
    }

    /**
     * Setters
     */

    public void setEmails(ArrayList<Email> emails){
        this.emails = emails;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCurrentSortingMethod(String currentSortingMethod){
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * Getters
     */
    public ArrayList<Email> getEmails(){
        return this.emails;
    }

    public String getName(){
        return this.name;
    }
    public String getCurrentSortingMethod(){
        return this.currentSortingMethod;
    }

    /**
     * Adds email to folder
     * @param email email to be added to folder
     */


    public void addEmail(Email email){
        emails.add(email);
    }

    /**
     * Removes an email
     * @param index index of email to be removed
     * @return removed email
     */
    public Email removeEmail(int index){
        if(index < -1 || index > emails.size()-1){
            throw new IllegalArgumentException("Illegal index");
        }
        return emails.remove(index);
    }

    /**
     * Sorts the email by ascending date
     */
    public void sortByDateAscending(){
        Collections.sort(emails, new DateAscending());
        currentSortingMethod = "DA";
    }

    /**
     * Sorts the emails by descending date
     */

    public void sortByDateDescending(){
        Collections.sort(emails, new DateDescending());
        currentSortingMethod = "DD";
    }

    /**
     * sorts the emails by ascending subjects
     */

    public void sortBySubjectAscending(){
        Collections.sort(emails, new SubjectAscending());
        currentSortingMethod = "SA";
    }

    /**
     * Sorts the emails by desceding subjects
     */

    public void sortBySubjectDescending(){
        Collections.sort(emails, new SubjectDescending());
        currentSortingMethod = "SD";
    }


}