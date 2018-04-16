public class Account {
    public static int accountCounter = 0;

    private int clientID;
    private String firstName;
    private String lastName;
    private String pesel;
    private String address;
    private double balance;

    public Account(String firstName, String lastName, String pesel, String address, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.address = address;
        this.balance = balance;
        this.clientID = accountCounter++;
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
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                " clientID =" + clientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
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