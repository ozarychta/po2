import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


class GuessNumberGame{
  private int tries;
  private final int N;
  private int randomNumber;
  private Random random = new Random();
  private Scanner scanner = new Scanner(System.in);

  public GuessNumberGame(int n){
    N = n;
  }

  public void playGame(){
    do{
      tries=0;
      randomNumber = random.nextInt(N+1);
      boolean isGuessed;

      System.out.println("\nWelcome in GuessNumberGame!");
      System.out.println("Your challenge is to guess a number I randomly chose from range [0,"+N+"]");
      System.out.println(randomNumber);

      do {
        int guess = getUserGuess();
        isGuessed = checkGuess(guess);
      }while(!isGuessed);


    } while(checkIfWantsToPlayAgain());

  }
  private boolean checkIfWantsToPlayAgain(){
    boolean wantsToPlayAgain=false;
    String answer=" ";
    boolean isValid;
    do{
      System.out.println("\nEnter 'YES' if you want to play the game again or 'NO' if you don't");
      try{
        answer = scanner.nextLine();
      } catch (Exception e){
        System.out.println(e.getMessage());
        e.printStackTrace();
      }

      if(answer.equalsIgnoreCase("YES")) {
        wantsToPlayAgain = true;
        isValid = true;
      }
      else if(answer.equalsIgnoreCase("NO")) {
        wantsToPlayAgain = false;
        isValid = true;
      }
      else isValid = false;
    } while (!isValid);

    return wantsToPlayAgain;
  }

  private int getUserGuess(){
    int userGuess=-1;
    boolean isValid;

    do{
      isValid = true;
      System.out.println("\nGuess what number I'm thinking about...:");
      try{
        userGuess = scanner.nextInt();
      } catch (InputMismatchException e1){
        System.out.println("\nYou entered something weird...");
        System.out.println("You have to enter an integer number from range [0,"+N+"]");
        isValid = false;
      } catch (Exception e2){
        System.out.println(e2.getMessage());
        e2.printStackTrace();
      } finally {
        scanner.nextLine();
      }
    } while(!isValid);

    return userGuess;
  }

  private boolean checkGuess(int userGuess){
      tries++;
      if(userGuess==randomNumber){
        System.out.println("Wow! You guessed in " + tries + " try! Congratulations!");
        return true;
      } else if(userGuess<randomNumber){
        System.out.println("Too less!");
      } else {
        System.out.println("Too much!");
      }
      return false;
  }

}
