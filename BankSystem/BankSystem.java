import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankSystem {

    private Scanner s = new Scanner(System.in);
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public BankSystem(){
    }

    private void printMenu(){
        System.out.println("Available options:");
        System.out.println("\ta\tAdd new account");
        System.out.println("\tr\tRemove existing account");
        System.out.println("\td\tDeposit money to chosen account");
        System.out.println("\tw\tWithdraw money from chosen account");
        System.out.println("\tt\tTransfer money between accounts");
        System.out.println("\tp\tPrint account/s meeting chosen criterion");
        System.out.println("\tpa\tPrint all accounts");
        System.out.println("\tq\tQuit BankSystem");
    }

    public void start(){
        System.out.println("Welcome in BankSystem application.");
        printMenu();

        boolean quit = false;
        String option;
        do {
            System.out.println("\nChoose option ... ( h - print available options) :");
            option = s.nextLine();
            switch (option.toUpperCase()) {
                case "H":
                    printMenu();
                    break;
                case "A":
                    addNewAccount();
                    break;
                case "R":
                    removeAccount();
                    break;
                case "D":
                    deposit();
                    break;
                case "W":
                    withdraw();
                    break;
                case "T":
                    transferMoney();
                    break;
                case "P":
                    printAccountsByCriterion();
                    break;
                case "PA":
                    printAll();
                    break;
                case "Q":
                    if(confirmOperation("Quit BankSystem application")){
                        quit = true;
                    } else{
                        System.out.println("Operation aborted.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (!quit);

    }

    private void addNewAccount(){
        String firstName, lastName, pesel, address;
        double balance;

        System.out.println("\nEnter new user's data:" );
        System.out.println("First name :");
        firstName = getWordFromUser();
        System.out.println("Last name : ");
        lastName = getWordsFromUser();
        System.out.println("PESEL : ");
        pesel = getPeselFromUser();
        System.out.println("\nEnter new user's address:" );
        System.out.println("City: ");
        address = getWordFromUser();
        System.out.println("Street: ");
        address = address + " St." + getWordsFromUser();
        System.out.println("House number: ");
        address = address + " " + getHouseNumberFromUser();

        System.out.println("Opening balance : ");
        balance = getValidMoneyAmountFromUser();

        if (confirmOperation("add following account: " +
                "Account{" +
                " firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}')) {
            Account newAccount = new Account(firstName, lastName, pesel, address, balance);
            accounts.put(newAccount.getClientID(), newAccount);
        } else {
            System.out.println("Operation aborted.");
        }
    }

    private void removeAccount(){
        if(accounts.isEmpty()){
            System.out.println("Accounts database is empty. Operation aborted.");
        } else {
            int clientID = getValidClientIDFromUser();
            if(confirmOperation("delete that account: "+accounts.get(clientID))){
                accounts.remove(clientID);
            } else {
                System.out.println("Operation aborted.");
            }
        }
    }

    private void deposit(){
        if(accounts.isEmpty()){
            System.out.println("Accounts database is empty. Operation aborted.");
        } else {
            int clientID = getValidClientIDFromUser();
            double moneyToDeposit = getValidMoneyAmountFromUser();
            if(confirmOperation("deposit "+moneyToDeposit+"$ on account with ID = "+clientID)){
                accounts.get(clientID).addToBalance(moneyToDeposit);
            } else {
                System.out.println("Operation aborted.");
            }
        }
    }

    private void withdraw(){
        if(accounts.isEmpty()){
            System.out.println("Accounts database is empty. Operation aborted.");
        } else {
            int clientID = getValidClientIDFromUser();
            double moneyToWithdraw = getValidMoneyAmountFromUser();
            if(confirmOperation("withdraw "+moneyToWithdraw+"$ from account with ID = "+clientID)){
                boolean withdrawalSucceeded = accounts.get(clientID).withdrawFromBalance(moneyToWithdraw);
                if(!withdrawalSucceeded){
                    System.out.println("There is not enough money on this account. \nOperation aborted.");
                }
            } else {
                System.out.println("Operation aborted.");
            }
        }
    }

    private void transferMoney(){
        if(accounts.isEmpty()){
            System.out.println("Accounts database is empty. Operation aborted.");
        } else {
            System.out.println("Choose account FROM which money will be transferred.");
            int sourceClientID = getValidClientIDFromUser();
            System.out.println("Choose account TO which money will be transferred.");
            int destinationClientID = getValidClientIDFromUser();
            System.out.println("Enter amount of money to be transferred.");
            double moneyToTransfer = getValidMoneyAmountFromUser();

            if(confirmOperation("transfer "+moneyToTransfer+
                    "$ from account with ID = "+sourceClientID +
                    "to account with ID = "+destinationClientID)){
                boolean withdrawalSucceeded = accounts.get(sourceClientID).withdrawFromBalance(moneyToTransfer);
                if(withdrawalSucceeded){
                    accounts.get(destinationClientID).addToBalance(moneyToTransfer);
                } else{
                    System.out.println("There is not enough money on this account. \nOperation aborted.");
                }

            } else {
                System.out.println("Operation aborted.");
            }
        }

    }

    private void printAll(){
        if(accounts.isEmpty()){
            System.out.println("Accounts database is empty. Nothing to print.");
        } else{
            if(confirmOperation("print all accounts")){
                for(Map.Entry<Integer, Account> entry: accounts.entrySet()){
                    System.out.println(entry.getValue());
                }
            } else{
                System.out.println("Operation aborted.");
            }
        }
    }

    private Map<Integer, Account> findAccountsByCriterion(){
        String criterionType = getCriterionTypeFromUser();
        final String criterionValue;
        Predicate<Map.Entry<Integer, Account>> predicate = entry -> true;
        switch (criterionType) {
            case "F":
                System.out.println("Enter first name to search for.");
                criterionValue = getWordFromUser();
                predicate = entry -> entry.getValue().getFirstName().matches(criterionValue);
                break;
            case "L":
                System.out.println("Enter last name to search for.");
                criterionValue = getWordsFromUser();
                predicate = entry -> entry.getValue().getLastName().matches(criterionValue);
                break;
            case "P":
                System.out.println("Enter pesel to search for.");
                criterionValue = getPeselFromUser();
                predicate = entry -> entry.getValue().getPesel().matches(criterionValue);
                break;
            case "C":
                System.out.println("Enter city to search for.");
                criterionValue = getWordFromUser();
                predicate = entry -> entry.getValue().getAddress().matches(criterionValue);
                break;
            case "S":
                System.out.println("Enter street to search for.");
                criterionValue = getWordsFromUser();
                predicate = entry -> entry.getValue().getAddress().matches(criterionValue);
                break;
            case "N":
                System.out.println("Enter house number to search for.");
                criterionValue = getHouseNumberFromUser();
                predicate = entry -> entry.getValue().getAddress().matches(criterionValue);
                break;
            case "A":
                String temp;
                System.out.println("Enter whole address to search for.");
                System.out.println("City: ");
                temp = getWordFromUser();
                System.out.println("Street: ");
                temp = temp + " St." + getWordsFromUser();
                System.out.println("House number: ");
                criterionValue = temp + " " + getHouseNumberFromUser();
                predicate = entry -> entry.getValue().getAddress().matches(criterionValue);
                break;
            case "B":
                System.out.println("Enter bilance to search for.");
                double doubleCriterionValue = getValidMoneyAmountFromUser();
                predicate = entry -> entry.getValue().getBalance()==doubleCriterionValue;
                break;
        }

        Map<Integer,Account> foundAccounts = accounts.entrySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        return foundAccounts;
    }

    private void printAccountsByCriterion(){
        if(accounts.isEmpty()){
            System.out.println("Accounts database is empty. Nothing to print.");
        } else{
            Map<Integer, Account> foundAccounts = findAccountsByCriterion();

            if(!foundAccounts.isEmpty()){
                if(confirmOperation("print accounts that meet the criterium")){
                    for(Map.Entry<Integer, Account> entry: foundAccounts.entrySet()){
                        System.out.println(entry.getValue());
                    }
                } else{
                    System.out.println("Operation aborted.");
                }
            } else {
                System.out.println("There is no account that meets the criterion.");
            }
        }
    }

    private boolean confirmOperation(String operationDescription){
        String answer;
        boolean confirmed = false;
        System.out.println("Are you sure you want to " + operationDescription + "?");
        do {
            System.out.println("Enter 'YES' or 'NO' :");
            answer = s.nextLine();
            if (answer.equalsIgnoreCase("NO")) {
                confirmed = false;
            } else if(answer.equalsIgnoreCase("YES")){
                confirmed = true;
            }
        } while (!answer.equalsIgnoreCase("YES" ) && !answer.equalsIgnoreCase("NO" ));
        return confirmed;
    }

    private int getValidClientIDFromUser(){
        int clientID=-1;
        boolean isValid;

        do{
            isValid = true;
            System.out.println("\nEnter clientID:");
            try{
                clientID = s.nextInt();
                if(!accounts.containsKey(clientID)){
                    System.out.println("There is no account with that UserID");
                    isValid = false;
                }
            } catch (InputMismatchException e){
                System.out.println("\nInvalid value type. ID must be an integer value.");
                isValid = false;
            } finally {
                s.nextLine();
            }
        } while(!isValid);

        return clientID;
    }

    private double getValidMoneyAmountFromUser(){
        double amount = -1.0;
        boolean isValid;

        do{
            isValid = true;
            System.out.println("\nEnter amount of money:");
            try{
                amount = s.nextDouble();
            } catch (InputMismatchException e){
                System.out.println("\nInvalid value type. Amount of money must be a double value.");
                isValid = false;
            } finally {
                s.nextLine();
            }
        } while(!isValid);

        amount = Math.round(amount*100) / 100; //rounds to 2 decimal places
        return Math.abs(amount);
    }

    private String getWordFromUser(){
        String word;
        do{
            System.out.println("Please enter valid data... (only letters expected) : ");
            word = s.nextLine().trim();
        }while(!(word.matches("^[a-zA-Z]{2,}$")));
        return word;
    }

    private String getWordsFromUser(){
        String word;
        do{
            System.out.println("Please enter valid data... (only letters, space and '-' between words expected) : ");
            word = s.nextLine().trim();
        }while(!(word.matches("^[a-zA-Z]{2,}([-\\s][a-zA-Z]{2,})*$")));
        return word;
    }

    private String getHouseNumberFromUser(){
        String word;
        do{
            System.out.println("Please enter valid data... (number or number followed by one letter f.e. '12A') : ");
            word = s.nextLine().trim();
        }while(!(word.matches("^[0-9]+[a-zA-Z]*$")));
        return word;
    }

    private String getPeselFromUser(){
        String pesel;
        do{
            System.out.println("Please enter valid data... (11 numbers) : ");
            pesel = s.nextLine().trim();
        }while(!(pesel.matches("^[0-9]{11}$")));
        return pesel;
    }

    private String getCriterionTypeFromUser(){
        System.out.println("Available options:");
        System.out.println("\tf\tSearch by first name");
        System.out.println("\tl\tSearch by last name");
        System.out.println("\tp\tSearch by pesel");
        System.out.println("\tc\tSearch by city");
        System.out.println("\ts\tSearch by street");
        System.out.println("\tn\tSearch by house number");
        System.out.println("\ta\tSearch by whole address");
        System.out.println("\tb\tSearch by account balance");

        String criterion;
        do {
            System.out.println("\nChoose criterion :");
            criterion = s.nextLine().toUpperCase().trim();
            if(!criterion.matches("^[FLPCSHAB]$")){
                System.out.println("Invalid criterion.");
            }
        } while (!criterion.matches("^[FLPCSHAB]$"));

        return criterion;
    }
}
