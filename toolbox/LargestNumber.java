/*
As the last question of a successful interview, your boss gives you a few pieces
of paper with numbers on them and asks you to compose the largest number from
these numbers. The resulting number is going to be your salary, so you are very
interested in maximizing this number.

Given n integers a(i), a(i+1), ... , a(n), compose the largest number possible
using the integers a(i) ... a(n). 1 <= n <= 100, 1 <= a(i) <= 10^3 for all i

Example: given 5 and 51, 551 > 515, so 551 is the preferred arrangement.

The feedback for this solution was:
Good job! (Max time used: 0.24/1.50, max memory used: 32235520/536870912.)
*/

import java.util.*;

public class LargestNumber {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.next();
        }
        System.out.println(maximize(a));
    }
    
    private static String maximize(String[] a) {
        //Sort
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int ij = Integer.parseInt(a[i] + a[j]);
                int ji = Integer.parseInt(a[j] + a[i]);
                if (ij < ji) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    j--;
                }
            }
        }
        
        //Concatenate
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }
}
