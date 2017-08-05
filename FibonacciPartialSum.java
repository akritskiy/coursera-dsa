//Given 0 <= m <= n <= 10^18, compute the last digit of the sum of F(m) + F(m+1) + ... + F(n)
//As usual, F(n) is the nth Fibonacci number

import java.util.*;

public class FibonacciPartialSum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        long m = input.nextLong();
        long n = input.nextLong();

        System.out.println(fibSumRange(m, n));
    }
    
    private static long fibSumRange(long m, long n) {
        long start = m % 60;
        long end = n % 60;

        if (end <= 1) {
            return end;
        }
        
        if (start <= 1) {
            return fibSumLast(end); //from the FibonacciSumLastDigit problem
        }
        
        long temp;
        long first = 0;
        long second = 1;

        for (int i = 2; i < start; i++) {
            temp = first;
            first = second;
            second = (second + temp) % 10;
        }

        long sum = 0;
        for (long i = start; i < end + 1; i++) {
            temp = first;
            first = second;
            second = (second + temp) % 10;
            sum = (sum + second) % 10;
        }

        return sum;
    }

    //returns the last digit of the sum F(0) + F(1) + ... + F(n)
    private static long fibSumLast(long n) {
        long m = n % 60;
        
        if (m <= 1)
            return m;
        
        long temp;
        long first = 0;
        long second = 1;
        long sum = 1;

        for (int i = 2; i < m + 1; i++) {
            temp = first;
            first = second;
            second = (second + temp) % 10;
            sum = (sum + second) % 10;
        }

        return sum;
    }
}
