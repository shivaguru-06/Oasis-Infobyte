import java.util.*;
import java.lang.*;
public class Account {
    private String name;//The name of account like saving,current etc
   // private double balance; //current balance of this account
    private String uuid;//account id number
    private User holder;//the user object owns this account 
    private ArrayList<Transaction> transactions;//list of transaction for this account
    /**
     * @param holder  the User object that holds this account 
     * @param theBank  the bank that issues the account 
     */
    public Account(String name,User holder,Bank theBank){
        //set   the account name and holder 
        this.name = name;
        this.holder = holder;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        //init transactions
        this.transactions = new ArrayList<Transaction>();
         
    }
    public String getUUID(){
        return this.uuid;
    }
    public String getSummaryLine(){
        //get the account's balance
        double balance = this.getBalance();
        //format the summary line, depending on the wheather the balance is negative
        if(balance>=0){
            return String.format(this.uuid+" : $"+balance+" : "+this.name);
        }else{
            return String.format(this.uuid+" : $"+balance+" : "+this.name);
        }
    }
    public double getBalance(){
        double balance = 0;
        for(Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }
    public void printTransHistory(){
        System.out.println("Transaction history for account : "+this.uuid);
        for(int t = this.transactions.size()-1;t>=0;t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }
    public void addTransaction(double amount,String memo){
        //create new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount,memo,this);
        this.transactions.add(newTrans);
    }
}