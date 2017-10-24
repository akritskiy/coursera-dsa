/* Compute F(n) mod m, where F(n) is the nth Fibonacci number, n can be as large
/* as 10^18, and m can be as large as 10^5 */

import java.util.Scanner;

public class FibonacciHuge {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(fibMod(input.nextLong(), input.nextLong()));
    }

    private static long fibMod(long n, long m) { // returns F(n) mod m
        return fib(n % pisano(m), m) % m;
    }

    private static long fib(long n, long m) { // returns the relevant digits of the nth Fibonacci number
        if (n == 0 || n == 1) {
            return n;
        }
        
        long temp;
        long first = 0;
        long second = 1;
        
        for (int i = 2; i < n + 1; i++) {
            temp = first;
            first = second;
            second = (second + temp) % m;
        }
        return second;
    }

    private static long pisano(long m) { // returns pisano period of m
        long temp;
        long first = 0;
        long second = 1;

        long count = 0; // initial length of the pisano period
        
        while (true) {
            temp = first;
            first = second;
            second = (second + temp) % m;
            count++;

            if (first == 0 && second == 1) {
                return count;
            } // every pisano period begins with 0 1. When we see 0 1, we know len of pisano period.
        }
    }
}
