/* Compute the last digit of the sum of F(m) + F(m+1) + ... + F(n). F(n) is the
nth Fibonacci number, and m and n can be as large as 10^18 */

import java.util.Scanner;

public class FibonacciPartialSum {
    private static long fibSumRange(long m, long n) {
        long start = m % 60;
        long end = n % 60;

        if (end <= 1) {
            return end;
        }
        if (start <= 1) {
            return fibSumLast(end); // from the FibonacciSumLastDigit problem
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

    private static long fibSumLast(long n) { // returns the last digit of the sum F(0) + F(1) + ... + F(n)
        long m = n % 60;
        
        if (m <= 1) {
            return m;
        }
        
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(fibSumRange(input.nextLong(), input.nextLong()));
    }
}