import java.util.*;

public class LCM {
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);

    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(fastLCM(a, b));
  }

  //solution: given two positive integers a and b, LCM(a,b) = a / GCD(a,b) * b
  private static long fastLCM(int a, int b) {
    int gcd = fastGCD(a, b);
    return (long) a / gcd * b;
  }

  //returns greatest common denominator of a and b
  private static int fastGCD(int a, int b) {
    //Code as if a > b
    //However, if b > a...
    if (b > a) {
      int temp = a;
      a = b;
      b = temp;
    }

    int remainder = a % b;
    while (remainder != 0) {
      a = b;
      b = remainder;
      remainder = a % b;
    }
    return b;
  }
}
