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
    solveEquation();
  }

  private void countDelta(){
    this.delta = Math.pow(b,2) - 4*a*c;
  }

  public void solveEquation(){
    this.x = new double[2];
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
    System.out.println("\nEntered equation: " + a + " x^2 + " + b + " x +" + c+"\n");
    System.out.println("Delta = "+delta);
    System.out.print("Your equation ");
      
   /* if(delta>0){
      System.out.println("has 2 results: x1=" + String.format("%.4f", x[0]) + " and x2=" +String.format("%.4f", x[1]));
    } else if(delta<0){
      System.out.println("has no result");
    } else {
      System.out.println("has 1 result x=" + String.format("%.4f", x[0]));
    }
    */
      
      if(delta>0){
      System.out.println("has 2 results: x1=" + x[0] + " and x2=" +x[1]);
    } else if(delta<0){
      System.out.println("has no result");
    } else {
      System.out.println("has 1 result x=" + x[0]);
    }
  }

  
}
