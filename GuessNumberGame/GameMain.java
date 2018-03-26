class GameMain{
    public static void main(String[] args){
    if(args.length != 1){
        System.out.println("Wrong number of arguments!");
        System.out.println("1 integer number greater that 0 was expected. You entered "+args.length+" arguments.");
      System.exit(1);
    }

    int n = 0;
    try{
      n = Integer.parseInt(args[0]);
      if(n<=0){
        System.out.println("End of the range can't be negative or equal to 0");
        System.exit(1);
      }
    } catch(NumberFormatException e){
      System.out.println("Wrong argument format!");
      System.out.println("1 integer number greater that 0 was expected.");
      System.exit(1);
    }

    GuessNumberGame game = new GuessNumberGame(n);
    game.playGame();


  }
}
