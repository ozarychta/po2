package sample.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Account {
    private SimpleIntegerProperty clientID;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty pesel;
    private SimpleStringProperty address;
    private SimpleDoubleProperty balance;

    public Account() {
        this.clientID = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.pesel = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.balance = new SimpleDoubleProperty();
    }

    public Account(int clientID, String firstName, String lastName, String pesel, String address, double balance) {
        this.clientID.set(clientID);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.pesel.set(pesel);
        this.address.set(address);
        this.balance.set(balance);
    }

    public int getClientID() {
        return clientID.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getPesel() {
        return pesel.get();
    }

    public String getAddress() {
        return address.get();
    }

    public double getBalance() {
        return balance.get();
    }

    public void setClientID(int clientID) {
        this.clientID.set(clientID);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public void addToBalance(double moneyToAdd){
        balance.set(balance.get()+moneyToAdd);
    }

    public boolean withdrawFromBalance(double moneyToWithdraw){
        if(balance.get() >= moneyToWithdraw){
            balance.set(balance.get()-moneyToWithdraw);
            balance.set(Math.round(balance.get()*100.0) / 100.0);
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                " \n\tclientID = " + clientID.get() +
                ", \n\tfirstName = '" + firstName.get() + '\'' +
                ", \n\tlastName = '" + lastName.get() + '\'' +
                ", \n\tpesel = '" + pesel.get() + '\'' +
                ", \n\taddress = '" + address.get() + '\'' +
                ", \n\tbalance = " + balance.get()  +
                "$\n\t}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return pesel.get().equals(account.pesel.get());
    }

    @Override
    public int hashCode() {
        return pesel.get().hashCode();
    }
}
