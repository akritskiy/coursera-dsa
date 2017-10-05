//Given an integer n, find the last digit of the sum F(0) + F(1) + ... + F(n)
//F(n) is the nth Fibonacci number
//n can be as large as 10^14

import java.util.*;

public class FibonacciSumLastDigit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        System.out.println(fibSumLast(n));
    }
    
    private static long fibSumLast(long n) {
        long m = n % 60; //the sequence of the last digit of sums is periodic, period length of 60
        
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
