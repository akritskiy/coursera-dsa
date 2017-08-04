import java.util.*;

public class FibonacciHuge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(fibMod(n, m));
    }
    
    //returns F(n) mod m. F(n) is the nth Fibonacci number.
    private static long fibMod(long n, long m) {
        long i = n % pisano(m);
        return fib(i, m) % m;
    }
    
    //returns the relevant digits of F(n)
    private static long fib(long n, long m) {
        if (n <= 1) {
            return n;
        }
        
        long temp;
        long first = 0;
        long second = 1;
        
        for (int i=2; i<n+1; i++) {
            temp = first;
            first = second;
            second = (second + temp) % m; //this % m is crucial, otherwise fib(n,m) fails at large n's
        }
        return second;
    }
    
    //returns pisano period of m
    private static long pisano(long m) {
        long temp;
        long first = 0;
        long second = 1;
        long count = 2; //initial length of the pisano period
        long result = -1;
        
        while (true) {
            temp = first;
            first = second;
            second = (second + temp) % m;
            count++;
            
            //every pisano period begins with 0 1. When we see the sequence 0 1, we know the length of the pisano period is count - 2.
            if (first == 0 && second == 1) {
                result = count - 2;
                break;
            }
        }
        return result;
    }
    
    //"naive" starter method, provided by instructors, kept for comparison and testing at small n's
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }
}
