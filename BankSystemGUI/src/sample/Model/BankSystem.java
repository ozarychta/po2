package sample.Model;

import sample.Model.Account;

import java.util.HashMap;
import java.util.InputMismatchException;

public class BankSystem {



        private HashMap<Integer, Account> accounts = new HashMap<>();
        private int accountCounter = 0;

        public BankSystem(){
            //
        }

//        private void addNewAccount(){
//            String firstName, lastName, pesel, address;
//            double balance;
//
//            System.out.println("\nEnter new user's data:" );
//            System.out.println("First name :");
//            firstName = getWordFromUser();
//
//            System.out.println("\nLast name : ");
//            lastName = getWordsFromUser();
//
//            System.out.println("\nPESEL : ");
//            pesel = getPeselFromUser();
//
//            Map<Integer,Account> accountsWithTheSamePesel = accounts.entrySet()
//                    .stream()
//                    .filter(entry -> entry.getValue().getPesel().matches(pesel))
//                    .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
//
//            if(!accountsWithTheSamePesel.isEmpty()){
//                System.out.println("Account with that pesel already exist. Operation aborted.");
//            } else {
//                System.out.println("\nEnter new user's address:" );
//                System.out.println("City: ");
//                address = getWordFromUser();
//
//                System.out.println("\nStreet: ");
//                address = address + " St." + getWordsFromUser();
//
//                System.out.println("\nHouse number: ");
//                address = address + " " + getHouseNumberFromUser();
//
//                System.out.println("\nOpening balance : ");
//                balance = getValidMoneyAmountFromUser();
//
//                if (confirmOperation("add following account: \n" +
//                        "Account{" +
//                        "\n\tfirstName = '" + firstName + '\'' +
//                        ", \n\tlastName = '" + lastName + '\'' +
//                        ", \n\tpesel = '" + pesel + '\'' +
//                        ", \n\taddress = '" + address + '\'' +
//                        ", \n\tbalance = " + balance +
//                        "$\n\t}\n")) {
//                    Account newAccount = new Account(accountCounter, firstName, lastName, pesel, address, balance);
//                    accounts.put(newAccount.getClientID(), newAccount);
//                    accountCounter++;
//
//                } else {
//                    System.out.println("Operation aborted.");
//                }
//            }
//
//        }

//        private void removeAccount(){
//            if(accounts.isEmpty()){
//                System.out.println("Accounts database is empty. Operation aborted.");
//            } else {
//                int clientID = getValidClientIDFromUser();
//                if(!accounts.containsKey(clientID)){
//                    System.out.println("There is no account with that UserID. Operation aborted.");
//                } else{
//                    if(confirmOperation("delete that account: "+accounts.get(clientID))){
//                        accounts.remove(clientID);
//
//                    } else {
//                        System.out.println("Operation aborted.");
//                    }
//                }
//            }
//        }

//        private void deposit(){
//            if(accounts.isEmpty()){
//                System.out.println("Accounts database is empty. Operation aborted.");
//            } else {
//                int clientID = getValidClientIDFromUser();
//                if(!accounts.containsKey(clientID)){
//                    System.out.println("There is no account with that UserID. Operation aborted.");
//                } else{
//                    System.out.println("Enter amount of money to be deposited.");
//                    double moneyToDeposit = getValidMoneyAmountFromUser();
//                    if(confirmOperation("deposit "+moneyToDeposit+"$ on account with ID = "+clientID)){
//                        accounts.get(clientID).addToBalance(moneyToDeposit);
//
//                    } else {
//                        System.out.println("Operation aborted.");
//                    }
//                }
//            }
//        }

//        private void withdraw(){
//            if(accounts.isEmpty()){
//                System.out.println("Accounts database is empty. Operation aborted.");
//            } else {
//                int clientID = getValidClientIDFromUser();
//                if(!accounts.containsKey(clientID)){
//                    System.out.println("There is no account with that UserID. Operation aborted.");
//                } else{
//                    System.out.println("Enter amount of money to be withdrawn.");
//                    double moneyToWithdraw = getValidMoneyAmountFromUser();
//                    if(confirmOperation("withdraw "+moneyToWithdraw+"$ from account with ID = "+clientID)){
//                        boolean withdrawalSucceeded = accounts.get(clientID).withdrawFromBalance(moneyToWithdraw);
//                        if(!withdrawalSucceeded) {
//                            System.out.println("There is not enough money on this account. \nOperation aborted.");
//                        }
//                    } else {
//                        System.out.println("Operation aborted.");
//                    }
//                }
//            }
//        }

//        private void transferMoney(){
//            if(accounts.isEmpty()){
//                System.out.println("Accounts database is empty. Operation aborted.");
//            } else {
//                System.out.println("Choose account FROM which money will be transferred.");
//                int sourceClientID = getValidClientIDFromUser();
//                if(!accounts.containsKey(sourceClientID)){
//                    System.out.println("There is no account with that UserID. Operation aborted.");
//                } else{
//                    System.out.println("Choose account TO which money will be transferred.");
//                    int destinationClientID = getValidClientIDFromUser();
//                    if(!accounts.containsKey(destinationClientID)){
//                        System.out.println("There is no account with that UserID. Operation aborted.");
//                    } else{
//                        System.out.println("Enter amount of money to be transferred.");
//                        double moneyToTransfer = getValidMoneyAmountFromUser();
//
//                        if(confirmOperation("transfer "+moneyToTransfer+
//                                "$ from account with ID = "+sourceClientID +
//                                " to account with ID = "+destinationClientID)){
//                            boolean withdrawalSucceeded = accounts.get(sourceClientID).withdrawFromBalance(moneyToTransfer);
//                            if(withdrawalSucceeded){
//                                accounts.get(destinationClientID).addToBalance(moneyToTransfer);
//
//                            } else{
//                                System.out.println("There is not enough money on account with ID = "+sourceClientID+". \nOperation aborted.");
//                            }
//                        } else {
//                            System.out.println("Operation aborted.");
//                        }
//                    }
//                }
//            }
//        }


//        private Map<Integer, Account> findAccountsByCriterion(){
//            String criterionType = getCriterionTypeFromUser();
//            final String criterionValue;
//            Predicate<Map.Entry<Integer, Account>> predicate = entry -> true;
//            switch (criterionType) {
//                case "F":
//                    System.out.println("Enter first name to search for.");
//                    criterionValue = getWordFromUser();
//                    predicate = entry -> entry.getValue().getFirstName().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "L":
//                    System.out.println("Enter last name to search for.");
//                    criterionValue = getWordsFromUser();
//                    predicate = entry -> entry.getValue().getLastName().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "P":
//                    System.out.println("Enter pesel to search for.");
//                    criterionValue = getPeselFromUser();
//                    predicate = entry -> entry.getValue().getPesel().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "C":
//                    System.out.println("Enter city to search for.");
//                    criterionValue = getWordFromUser();
//                    predicate = entry -> entry.getValue().getAddress().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "S":
//                    System.out.println("Enter street to search for.");
//                    criterionValue = getWordsFromUser();
//                    predicate = entry -> entry.getValue().getAddress().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "N":
//                    System.out.println("Enter house number to search for.");
//                    criterionValue = getHouseNumberFromUser();
//                    predicate = entry -> entry.getValue().getAddress().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "A":
//                    String temp;
//                    System.out.println("Enter whole address to search for.");
//                    System.out.println("City: ");
//                    temp = getWordFromUser();
//                    System.out.println("Street: ");
//                    temp = temp + " St." + getWordsFromUser();
//                    System.out.println("House number: ");
//                    criterionValue = temp + " " + getHouseNumberFromUser();
//                    predicate = entry -> entry.getValue().getAddress().toLowerCase().contains(criterionValue.toLowerCase());
//                    break;
//                case "B":
//                    System.out.println("Enter bilance to search for.");
//                    double doubleCriterionValue = getValidMoneyAmountFromUser();
//                    predicate = entry -> entry.getValue().getBalance()==doubleCriterionValue;
//                    break;
//            }
//
//            Map<Integer,Account> foundAccounts = accounts.entrySet()
//                    .stream()
//                    .filter(predicate)
//                    .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
//
//            return foundAccounts;
//        }


        private int getValidClientIDFromUser(){
            int clientID=-1;
            boolean isValid;

            do{
                isValid = true;
                System.out.println("Enter clientID (integer value):");
                try{
                    //clientID = s.nextInt();
                    if(clientID<0){
                        System.out.println("ID must be a positive value");
                        isValid=false;
                    }
                } catch (InputMismatchException e){
                    System.out.println("\nInvalid value type. ID must be an integer value.");
                    isValid = false;
                } finally {
                    //s.nextLine();
                }
            } while(!isValid);

            return clientID;
        }

        private double getValidMoneyAmountFromUser(){
            double amount = -1.0;
            boolean isValid;

            do{
                isValid = true;
                System.out.println("Please enter valid data... (positive dauble value with comma as a decimal point) : ");
                try{
                    //amount = s.nextDouble();
                    if(amount<=0.0){
                        System.out.println("Amount of money must be a positive value");
                        isValid=false;
                    }
                } catch (InputMismatchException e){
                    System.out.println("Invalid value type.");
                    isValid = false;
                } finally {
                    //s.nextLine();
                }
            } while(!isValid);

            amount = Math.round(amount*100.0) / 100.0; //rounds to 2 decimal places
            return Math.abs(amount);
        }

//        private String getWordFromUser(){
//            String word;
//            do{
//                System.out.println("Please enter valid data... (only english letters (min length 2) expected) : ");
//               word = s.nextLine().trim();
//            }while(!(word.matches("^[a-zA-Z]{2,}$")));
//            return word;
//        }
//
//        private String getWordsFromUser(){
//            String word;
//            do{
//                System.out.println("Please enter valid data... (only english letters (min length 2), space and '-' between words expected) : ");
//                word = s.nextLine().trim();
//            }while(!(word.matches("^[a-zA-Z]{2,}([-\\s][a-zA-Z]{2,})*$")));
//            return word;
//        }
//
//        private String getHouseNumberFromUser(){
//            String word;
//            do{
//                System.out.println("Please enter valid data... (number or number followed by one letter f.e. '12A') : ");
//                word = s.nextLine().trim();
//            }while(!(word.matches("^[0-9]+[a-zA-Z]*$")));
//            return word;
//        }
//
//        private String getPeselFromUser(){
//            String pesel;
//            do{
//                System.out.println("Please enter valid data... (11 numbers) : ");
//                pesel = s.nextLine().trim();
//            }while(!(pesel.matches("^[0-9]{11}$")));
//            return pesel;
//        }
//
//        private String getCriterionTypeFromUser(){
//            System.out.println("Available options:");
//            System.out.println("\tf\tSearch by first name");
//            System.out.println("\tl\tSearch by last name");
//            System.out.println("\tp\tSearch by pesel");
//            System.out.println("\tc\tSearch by city");
//            System.out.println("\ts\tSearch by street");
//            System.out.println("\tn\tSearch by house number");
//            System.out.println("\ta\tSearch by whole address");
//            System.out.println("\tb\tSearch by account balance");
//
//            String criterion;
//            do {
//                System.out.println("\nChoose criterion :");
//                criterion = s.nextLine().toUpperCase().trim();
//                if(!criterion.matches("^[FLPCSNAB]$")){
//                    System.out.println("Invalid criterion.");
//                }
//            } while (!criterion.matches("^[FLPCSNAB]$"));
//
//            return criterion;
//        }
    }

