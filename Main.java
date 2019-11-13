import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    solver();   
    
  }
  
  public static void solver() {
    Scanner in = new Scanner(System.in);

    System.out.print("How many functions:");
    int nof = Integer.parseInt(in.nextLine());
    System.out.print("How many moves:");
    int m = Integer.parseInt(in.nextLine());
    System.out.print("Starting number:");
    int sf = Integer.parseInt(in.nextLine());
    System.out.print("Ending number:");
    int e = Integer.parseInt(in.nextLine());
    String[] fun = new String[10];
    for (int i = 0; i < nof; i++) {
      System.out.print("What is function number " + Integer.toString(i) + ':');
      fun[i] = in.nextLine();
    }
    in.close();

    int numOfWays = (int) Math.pow(nof, m);
    String[] moves = new String[numOfWays];
    for (int i = 0; i < numOfWays; i++) {
      moves[i] = Integer.toString(i, nof);
      System.out.println(moves[i]);
      while (moves[i].length() < m) {
        moves[i] = "0" + moves[i];
      }
    }
    for (int i = 0; i < numOfWays; i++) {
      double N = sf;
      for (int j = 0; j < m; j++) {
        String cm = fun[Integer.parseInt(moves[i].substring(j, j + 1))];
        N = fInt(N, cm);
      }
      if ((int) N == e) {
        System.out.print("\nSolution:");
        for (int j = 0; j < moves[i].length(); j++) {
          System.out.print("\t" + fun[Integer.parseInt(moves[i].substring(j, j + 1))]);
        }
      }
    }
  }

  public static double fInt(double N, String f) {
    if (f.contains("rep")) { // ##rep##
      String fn = f.substring(0,f.indexOf("r"));
      String sn = f.substring(f.indexOf("p")+1);
      String t = Integer.toString((int) N);
      for(int i = 0; i < t.length() - fn.length(); i++)
      {
        if(t.substring(i,i+fn.length()).compareTo(fn)==0)
        {
          t = t.substring(0,i) + sn + t.substring(i+fn.length());
        }
      }
      return Double.parseDouble(t);

    }
    if (f.contains("rev")) {
      String t = Integer.toString((int) N);
      String out = "";
      for (int i = 0; i < t.length(); i++) {
        out = out + t.substring(t.length() - i - 1, t.length() - i);
      }
      return Double.parseDouble(out);
    }
    if (f.contains("rem")) {
      String t = Integer.toString((int) N);
      String out = t.substring(0, t.length() - 1);
      try {
        return Double.parseDouble(out);
      } catch (Exception e) {
        return 0.0;
      }
    }
    if (f.contains("ins")) {
      String t = Integer.toString((int) N);
      String add = f.substring(3);
      String out = t + add;
      return Double.parseDouble(out);
    }
    if (f.contains("*")) {
      double mult = Double.parseDouble(f.substring(1));
      return mult * N;
    }
    if (f.contains("/")) {
      double mult = Double.parseDouble(f.substring(1));
      return N / mult;
    }
    if (f.contains("+")) {
      double mult = Double.parseDouble(f.substring(1));
      return mult + N;
    }
    if (f.contains("-")) {
      double mult = Double.parseDouble(f.substring(1));
      return N - mult;
    }
    return 0.0;
  }
}
