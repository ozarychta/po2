import java.util.ArrayList;

public class BankSystem {

    private ArrayList<Account> accounts;

    public BankSystem(){
        accounts = new ArrayList<>();
    }

    public void addAccount(Account newAccount){
        if(accounts.contains(newAccount)){
            System.out.println("That person's account already exist.");
        } else {
            accounts.add(newAccount);
        }
    }

    public void removeAccount(int accountNumber){
        accounts.remove(accountNumber);
    }

    private void deposit(int accountNumber, double moneyToDeposit){
        accounts.get(accountNumber).addToBalance(moneyToDeposit);
    }

    private void withdraw(int accountNumber, double moneyToDeposit){
        accounts.get(accountNumber).addToBalance(-moneyToDeposit);
    }

    public void printAll(){
        for(Account account: accounts){
            System.out.println(account);
        }
    }

    public void printAccount(int accountNumber){
        System.out.println(accounts.get(accountNumber));
    }

    public void printMenu(){
        System.out.println("Available options:");
        System.out.println("\ta\tAdd new account");
        System.out.println("\tr\tRemove existing account");
        System.out.println("\td\tDeposit money to chosen account");
        System.out.println("\tw\tWithdraw money from chosen account");
        System.out.println("\tt\tTransfer money between accounts");
        System.out.println("\tp\tPrint chosen account");
        System.out.println("\tpa\tPrint all accounts");
        System.out.println("\tm\tPrint this menu");
        System.out.println("\tq\tQuit BankSystem");
    }

//    public int chooseAccount(){
//
//    }

    public void start(){
        
    }


}
