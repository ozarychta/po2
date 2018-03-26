import java.util.Random;
import java.util.Scanner;

class GuessNumberGame{
  private int tries;
  private final int N;
  private int randomNumber;
  private Random random = new Random();

  public GuessNumberGame(int n){
    N = n;
  }

  public void playGame(){
    System.out.println("\nWelcome in GuessNumberGame!");
    System.out.println("Your challenge is to guess a number I randomly chose from range [0,"+N+"]");
    tries=0;

    randomNumber = random.nextInt(N+1);
    System.out.println(randomNumber);
    boolean isGuessed;
    do {
      int guess = getUserGuess();
      isGuessed = check(guess);
    }while(!isGuessed);
  }

  private int getUserGuess(){
    int userGuess=-1;
    boolean isValid;
    Scanner s = new Scanner(System.in);
    do{
      isValid = true;
      System.out.println("\nGuess what number I'm thinking about...:");
      try{
        userGuess = s.nextInt();
      } catch (Exception e){
        System.out.println("What? :o");
        System.out.println("You have to enter an integer number from range [0,"+N+"]");
        isValid = false;
        s.nextLine();
      }
    } while(!isValid);

    return userGuess;
  }

  private boolean check(int userGuess){
      tries++;
      if(userGuess==randomNumber){
        System.out.println("Wow! You guessed in " + tries + " try!");
        return true;
      } else if(userGuess<randomNumber){
        System.out.println("Too less!");
      } else {
        System.out.println("Too much!");
      }
      return false;
  }

}
