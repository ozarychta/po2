public class Account {

    private int clientID;
    private String firstName;
    private String lastName;
    private String pesel;
    private String address;
    private double balance;

    public Account(int clientID, String firstName, String lastName, String pesel, String address, double balance) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.address = address;
        this.balance = balance;
    }


    public int getClientID() {
        return clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void addToBalance(double moneyToAdd){
        balance += moneyToAdd;
    }

    public boolean withdrawFromBalance(double moneyToWithdraw){
        if(balance >= moneyToWithdraw){
            balance -= moneyToWithdraw;
            balance = Math.round(balance*100.0) / 100.0;
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                " \n\tclientID = " + clientID +
                ", \n\tfirstName = '" + firstName + '\'' +
                ", \n\tlastName = '" + lastName + '\'' +
                ", \n\tpesel = '" + pesel + '\'' +
                ", \n\taddress = '" + address + '\'' +
                ", \n\tbalance = " + balance  +
                "$\n\t}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return pesel.equals(account.pesel);
    }

    @Override
    public int hashCode() {
        return pesel.hashCode();
    }
}
