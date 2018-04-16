public class BankSystemMain {

    public static void main(String[] args) {
        if(args.length > 0){
            System.out.println("That program is not taking any arguments!");
            System.exit(1);
        }

        BankSystem bankSystem = new BankSystem();
        bankSystem.start();
    }
}
