import java.util.*;
public class Bank{
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUUID(){
        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;
        //continue looping until we get a unique ID
        do{
            //genrate number
            uuid="";
            for(int i = 0;i<len;i++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            //check  to make sure it's unique
            nonUnique = false;
            for(User u:this.users){
                if(uuid.compareTo(u.getUUID())==0){
                    nonUnique = true;
                    break;
                }
            }
        }while(nonUnique);
        return uuid;
    }
    public String getNewAccountUUID(){
      //inits
      String uuid;
      Random rng = new Random();
      int len = 10;
      boolean nonUnique;
      //continue looping until we get a unique ID
      do{
          //genrate number
          uuid="";
          for(int i = 0;i<len;i++){
              uuid += ((Integer)rng.nextInt(10)).toString();
          }
          //check  to make sure it's unique
          nonUnique = false;
          for(Account a:this.accounts){
              if(uuid.compareTo(a.getUUID())==0){
                  nonUnique = true;
                  break;
              }
          }
      }while(nonUnique);
      return uuid;  

    }
    public void addAccount(Account anAct){
        this.accounts.add(anAct);
    }

    public User addUser(String firstName,String lastName,String pin){
        //create a new user object and add it to our list
        User newUser = new User(firstName,lastName,pin,this);
        this.users.add(newUser);
        //create a saving account for the user and add user to User and Bank account lists
        Account newAccount = new Account("Savings",newUser,this);
     
         newUser.addAccount(newAccount);
         this.accounts.add(newAccount);

         return newUser;
    }
    public User userLogin(String userID,String pin){
        //search through lists of user
        for(User u:this.users){
            //check usser ID is correct
            if(u.getUUID().compareTo(userID)==0 && u.validatePin(pin)){
                return u;
            }
        }
        return null;//either pin or user invalid or both invalid

    }
    public String getName(){
        return this.name;
    }
}