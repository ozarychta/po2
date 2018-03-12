class Equation{
  private double a;
  private double b;
  private double c;
  private double delta;
  private double[] x;

  public Equation(double a, double b, double c){
    this.a = a;
    this.b = b;
    this.c = c;
    countDelta();
    this.x = new double[2];
    solveEquation();
  }

  private void countDelta(){
    this.delta = Math.pow(b,2) - 4*a*c;
  }

  public void solveEquation(){
    if(delta>0){
      x[0] = (-b - Math.sqrt(delta)) / (2*a);
      x[1] = (-b + Math.sqrt(delta)) / (2*a);
    } else if(delta<0){
      x[0] = 0;
      x[0] = 0;
    } else {
      x[0] = -b / (2*a);
      x[1] = 0;
    }
  }

  public void printResults(){
    System.out.println("Equation " + a + "x^2 + " + b + "x +" + c);

    if(delta>0){
      System.out.println(" has 2 results: x1=" + x[0] + " and x2="+x[1]);
    } else if(delta<0){
      System.out.println(" has no result");
    } else {
      System.out.println(" has 1 result x=" + x[0]);
    }
  }

  public static void main(String[] args){
    if(args.length != 3){
      System.out.println("Wrong number of arguments. 3 arguments expected.");
      return;
    }


    double a, b, c;
      try{
        a = Double.parseDouble(args[0]);
        b = Double.parseDouble(args[1]);
        c = Double.parseDouble(args[2]);
        if(a==0.0){
          System.out.println("First argument can't be equal to 0.");
          return;
        }

      } catch(NumberFormatException e){
        System.out.println("Wrong arguments format. Double arguments expected.");
        return;
      }

    Equation equation = new Equation(a, b, c);
    equation.printResults();
  }
}
