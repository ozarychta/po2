public class Account {
    private static int accountCounter = 0;

    private int accountNumber;
    private String name;
    private String surname;
    private String pesel;
    private String address;
    private double balance;

    public Account(String name, String surname, String pesel, String address, double balance) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.balance = balance;
        this.accountNumber = accountCounter++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void addToBalance(double moneyToAdd){
        balance += moneyToAdd;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account number='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
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
