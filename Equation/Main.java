class Main{
    public static void main(String[] args){
    if(args.length != 3){
        System.out.println("Wrong number of arguments!");
        System.out.println("3 double arguments were expected. You entered "+args.length+" arguments.");
      return;
    }


    double a, b, c;
      try{
        System.out.print("\nFirst argument = ");
        a = Double.parseDouble(args[0]);
        System.out.println(a);
        if(a==0.0){
          System.out.println("First argument can't be equal to 0 in quadratic equation!");
          return;
        }
        
        System.out.print("Second argument = ");
        b = Double.parseDouble(args[1]);
        System.out.println(b);
        
        System.out.print("Third argument = ");
        c = Double.parseDouble(args[2]);
        System.out.println(c);

      } catch(NumberFormatException e){
        System.out.println(" wrong argument format!");
        System.out.println("3 double arguments were expected");
        return;
      }
      
    
        
    Equation equation = new Equation(a, b, c);
    equation.printResults();
  }
}