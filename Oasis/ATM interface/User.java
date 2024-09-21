import java.util.*;
import java.security.*;//MessageDigest,NoSuchAlgorthmException
import java.lang.*;
public class User{
    private String firstName; //first name of user
    private String lastName;//last name of user
    private String uuid;//ID no of user
    private byte pinHash[];//MD5 hash of user's pin number
    private ArrayList<Account>accounts;//list of accounts for this user
    /**
     * Create a new user 
     * @param firstName - user's first name 
     * @parm lastName - user's last name 
     * @param pin - the user's account pin 
     * @param theBank - the Bank object that user is customer of
     */
    
    public User(String firstName,String lastName,String pin,Bank theBank){
        //set the user name     
        this.firstName = firstName;
        this.lastName = lastName;

        //store the pin's MD5 hash, rather than original value for security purpose
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        }catch(NoSuchAlgorithmException e){
            System.err.println("error,caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a  new, unique universal ID for user 
        this.uuid = theBank.getNewUserUUID();
        
        //create emptylist of accounts
        this.accounts = new ArrayList<Account>();

        //Print log message 
        System.out.println("New User : "+firstName+" "+lastName+" with ID : "+this.uuid+" created");
    }
    /**
     *  @param anAct the account to add
     */
    public void addAccount(Account anAct){
        this.accounts.add(anAct);
    }
    /**
     * return's the user's uuid
     */
    public String getUUID(){
        return this.uuid;
    }
    public boolean validatePin(String aPin){
         try{
             MessageDigest md = MessageDigest.getInstance("MD5");
             return MessageDigest.isEqual(md.digest(aPin.getBytes()),this.pinHash);
         }catch(NoSuchAlgorithmException e){
            System.err.println("error,caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
         }
         return false;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void printAccountSummary(){
        System.out.println(this.firstName+"'s account Summary :");
        for(int a = 0;a<this.accounts.size();a++){
            System.out.println((a+1)+" "+this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }
    public int numAccounts(){
        return this.accounts.size();
    }
    /**
     * @param actInx the index of the account to use
     */
    public void printActTransHistory(int actIdx){
        this.accounts.get(actIdx).printTransHistory();
    }
    public double getAccountBalance(int actIdx){
        return this.accounts.get(actIdx).getBalance();
    }
    public String getActUUID(int actIdx){
        return this.accounts.get(actIdx).getUUID();
    }
    public void addActTransaction(int actIdx,double amount,String memo){
        this.accounts.get(actIdx).addTransaction(amount,memo);
    }
}