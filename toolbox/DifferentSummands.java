/* The goal of this problem is to represent a positive integer as a sum of as
many distinct positive integers as possible. That is, given an integer n, find
the max k such that n = a1 + a2 + ... + ak, and no two ai are equal. n can be as
large as 10^9.

For example:
Input: 6
Output:
3
1 2 3

Input: 8
Output:
3
1 2 5

The feedback for this problem was:
Good job! (Max time used: 0.61/1.50, max memory used: 50421760/536870912.) */

import java.util.*;

public class DifferentSummands{
	private static List<Integer> optimalSummands(int n) {
		List<Integer> summands = new ArrayList<Integer>();
		
		for (int i = 1; i <= n; i++) {
			if (2 * i + 1 > n) {
				summands.add(n);
				break;
			}
			summands.add(i);
			n -= i;
		}
		return summands;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<Integer> summands = optimalSummands(input.nextInt());
        
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
	}
}
