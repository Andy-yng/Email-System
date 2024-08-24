/**
 * The <code>Email</code> creates emails that
 * contains different information
 * @author Andy Yang
 *    email:andy.yang.2@stonybrook.edu
 *    SBU ID: 115104866
 */
import java.io.Serializable;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Email implements Serializable, Comparable{

    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     * Constructor for Email
     */


    public Email(){
        this.to = null;
        this.cc = null;
        this.bcc = null;
        this.subject = null;
        this.body = null;
        GregorianCalendar newCal =  new GregorianCalendar();
        this.timestamp = newCal;
    }

    /**
     * Constructor for Email with five parameters
     * @param to recipient
     * @param cc cc
     * @param bcc cc
     * @param subject subject of email
     * @param body message
     */

    public Email(String to, String cc, String bcc, String subject, String body){
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = new GregorianCalendar();
    }
    /**
     * Setters
     */
    public void setTo(String to){
        this.to = to;
    }
    public void setCc(String cc){
        this.cc= cc;
    }
    public void setBcc(String bcc){
        this.bcc = bcc;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public void setBody(String body){
        this.body = body;
    }
    public void setTimestamp(GregorianCalendar timestamp){
        this.timestamp = timestamp;
    }
    /**
     * Getters
     */
    public String getTo(){
        return this.to;
    }
    public String getCc(){
        return this.cc;
    }
    public String getBcc(){
        return this.bcc;
    }
    public String getSubject(){
        return this.subject;
    }
    public String getBody(){
        return this.body;
    }
    public GregorianCalendar getTimestamp(){
        return this.timestamp;
    }
    /**
     * Compares two objects to see which one is greater
     * @param object the object to be compared.
     * @return 1 or 0 or -1
     */

    public int compareTo(Object object){
        if(object instanceof Email){
            Email comp = (Email) object;
            String a = this.subject;
            String b = comp.getSubject();
            int compare = a.compareTo(b);
            if(compare > 0)
                return 1;
            else if(compare < 0)
                return -1;
            else
                return 0;
        }
        return 0;
    }

}

/**
 * Sorts the email in date ascending order
 */
class DateAscending implements Comparator{
    public int compare(Object o1, Object o2){
        Email email1 = (Email)o1;
        Email email2 = (Email)o2;
        return (email1.getTimestamp().compareTo(email2.getTimestamp()));
    }
}

/**
 * Sorts the email in date descending order
 */
class DateDescending implements Comparator{
    public int compare(Object e1, Object e2){
        Email email1 = (Email)e1;
        Email email2 = (Email)e2;
        return (email2.getTimestamp().compareTo(email1.getTimestamp()));
    }
}

/**
 * Sorts the email in Subject Ascending order
 */
class SubjectAscending implements Comparator{
    public int compare(Object e1, Object e2){
        Email email1 = (Email)e1;
        Email email2 = (Email)e2;
        return (email1.getSubject().compareTo(email2.getSubject()));
    }
}

/**
 * Sorts the emails in subject desceding order
 */
class SubjectDescending implements Comparator{
    public int compare(Object e1, Object e2){
        Email email1 = (Email)e1;
        Email email2 = (Email)e2;
        return (email2.getSubject().compareTo(email1.getSubject()));
    }
}