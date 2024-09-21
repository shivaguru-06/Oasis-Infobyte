import java.util.*;//Scanner
import java.lang.*;
public class ATM{
    public static void main(String[]args){
        //init Scanner 
        Scanner sc = new Scanner(System.in);
        //init bank
        Bank theBank = new Bank("State Bank Of India");
        //add user ,whichalso creates a savings account
        User aUser = theBank.addUser("Shiva","Guru","0610");
        //add a checking account for our user 
        Account newAccount = new Account("Checking",aUser,theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true){
            //stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank,sc);

            //stay in main menu until user quits
            ATM.printUserMenu(curUser,sc);
        }
    }
    public static User mainMenuPrompt(Bank theBank,Scanner sc){
        //init
        String userID;
        String pin;
        User authUser;
        //prompt the user for user id and pincombo until a correct one is reached
        do{
            System.out.println("\n\nWelcome to "+theBank.getName()+"\n\n");
            System.out.print("Enter User ID : ");
            userID = sc.nextLine();
            System.out.println();
            System.out.print("Enter Pin : ");
            pin = sc.nextLine();

            //try to get the user object corresponding to th ID and pin Combo 
            authUser = theBank.userLogin(userID,pin);
            if(authUser == null){
                System.out.println("incorrect User ID/Pin, Please try again");
            }
        }while(authUser==null);//continue login until successful login
        return authUser;
    }
    public static void printUserMenu(User theUser,Scanner sc){
        //print a summary of user's account
        theUser.printAccountSummary();

        //init choice
        int choice;
        //user menu
        do{
            System.out.println("Welcome "+theUser.getFirstName()+" What would you like to do");
            System.out.println("1>Show Transaction History");
            System.out.println("2>withdraw");
            System.out.println("3>Deposite");
            System.out.println("4>Transfer");
            System.out.println("5>Quit");
            System.out.println();
            System.out.println("Enter Your Choice : ");
            choice = sc.nextInt();
            if(choice<1 || choice>5){
                System.out.println("Invalid Choice"+" Please choose between 1-5 ");  
            } 
        }while(choice<1 || choice>5);
        //process the choice
        switch(choice){
            case 1:
                ATM.showTransactionHistory(theUser,sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser,sc);
                break;
            case 3:
                ATM.depositeFunds(theUser,sc);
                break;
            case 4:
                ATM.transferFunds(theUser,sc);
                break;
            case 5:
                 //gobble up rest of previous input
                 sc.nextLine();
                 break;
        }
        //redisplay this menu unless user quit
        if(choice != 5){
            ATM.printUserMenu(theUser,sc);
        }
    }
    public static void showTransactionHistory(User theUser,Scanner sc){
        int theAct;
        //get account whose history to look at
        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+" whose transaction you waant to see : ",theUser.numAccounts());
            theAct = sc.nextInt()-1;
            if(theAct < 0 || theAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again.....");
            }
        }while(theAct < 0 || theAct >= theUser.numAccounts());
        //Print transaction history
 
        theUser.printActTransHistory(theAct);
    }
    public static void transferFunds(User theUser,Scanner sc){
        //intits
        int fromAct;
        int toAct;
        double amount;
        double actBal;
        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer from:",theUser.numAccounts());
            fromAct = sc.nextInt()-1;
            if(fromAct < 0 || fromAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again.....");
            }
        }while(fromAct < 0 || fromAct >= theUser.numAccounts());
        actBal = theUser.getAccountBalance(fromAct);

        //get the account to transfer to 
        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer to:",theUser.numAccounts());
            toAct = sc.nextInt()-1;
            if(toAct < 0 || toAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again.....");
            }
        }while(toAct < 0 || toAct >= theUser.numAccounts());
        //get the amount to traansfer
        do{
            System.out.println("Enter the amount to transfer (max than $"+actBal+") : $");
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }else if(amount > actBal){
                System.out.println("Amount must not be greater than \n"+"balance of $"+actBal);
            }
        }while(amount < 0 || amount>actBal);
        //do the transfer
        theUser.addActTransaction(fromAct,-1*amount,String.format("Transfer to account "+theUser.getActUUID(toAct)));
        theUser.addActTransaction(toAct,amount,String.format("Transfer to account "+theUser.getActUUID(fromAct)));
    }
    public static void withdrawFunds(User theUser,Scanner sc){
        int fromAct;
        String memo;
        double amount;
        double actBal;
        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+"Where to withdraw :",theUser.numAccounts());
            fromAct = sc.nextInt()-1;
            if(fromAct < 0 || fromAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again.....");
            }
        }while(fromAct < 0 || fromAct >= theUser.numAccounts());
        actBal = theUser.getAccountBalance(fromAct);
        //get the amount to traansfer
        do{
            System.out.println("Enter the amount to withdraw (max $ "+actBal+"): $");
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }else if(amount > actBal){
                System.out.println("Amount must not be greater than \n"+"balance of $"+actBal);
            }
        }while(amount < 0 || amount>actBal);
        //gobble up rest of previous input
        sc.nextLine();
        //get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        //do withdrawal
        theUser.addActTransaction(fromAct,-1*amount,memo);        
    }
    public static void depositeFunds(User theUser,Scanner sc){
        int toAct;
        String memo;
        double amount;
        double actBal;
        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+"Where to Deposite:",theUser.numAccounts());
            toAct = sc.nextInt()-1;
            if(toAct < 0 || toAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again.....");
            }
        }while(toAct < 0 || toAct >= theUser.numAccounts());
        actBal = theUser.getAccountBalance(toAct);
        //get the amount to transfer
        do{
            System.out.print("Enter the amount to deposite (max than $"+actBal+") :$");
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }
        }while(amount < 0 );
        //gobble up rest of previous input
        sc.nextLine();
        //get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        //do withdrawal
        theUser.addActTransaction(toAct,amount,memo);             
    }
}