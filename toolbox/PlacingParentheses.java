/*
Given an arithmetic expression e.g. "5-8+7*4-8+9", find the max value of
the expression by specifying the order of operations, as you would on paper
by adding parentheses. For example, the value of the expression above is
maximized if the parens are placed as follows:
5 - ((8 + 7) * (4 - (8 + 9))) = 200

The input is a string, like the one above. The digits are limited to values
from 0 to 9. The operands are either +, -, or *. The expression is at most
29 symbols.

The feedback for this solution was:
Good job! (Max time used: 0.22/1.50, max memory used: 26087424/536870912.)
*/

import java.util.*;

public class PlacingParentheses {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String s = input.next();
		int len = s.length();
		int numInts = (len + 1) / 2;
		int numOperands = len - numInts;
		
		int[] ints = new int[numInts];
		int j = 0;
		char[] operands = new char[numOperands];
		int k = 0;

		for (int i = 0; i < len; i++) {
			if (i % 2 == 0) {
				ints[j] = Character.getNumericValue(s.charAt(i));
				j++;
			}
			else {
				operands[k] = s.charAt(i);
				k++;
			}
		}

		System.out.print(maxVal(ints, operands));
	}

	private static long maxVal(int[] ints, char[] operands) {
		int n = ints.length; //number of integers

		long[][] M = new long[n][n];
		long[][] m = new long[n][n];
		
		for (int i = 0; i < n; i++) {
			long temp = ints[i];
			M[i][i] = temp;
			m[i][i] = temp;
		}

		for (int s = 1; s < n; s++) {
			for (int i = 0; i < n - s; i++) {
				int j = i + s;
				long[] tempArr = minAndMax(M, m, operands, i, j);
				m[i][j] = tempArr[0];
				M[i][j] = tempArr[1];
			}
		}

		// //uncomment print statements to see what is happening in the matrices
		// System.out.println();
		// printArr(M);
		// System.out.println();
		// printArr(m);

		return M[0][n - 1];
	}

	private static long[] minAndMax(long[][] M, long[][] m, char[] operands, int i, int j) {
		long min = Integer.MAX_VALUE;
		long max = Integer.MIN_VALUE;
		long a, b, c, d;
		for (int k = i; k <= j - 1; k++) {
			a = compute(M[i][k], M[k + 1][j], operands[k]);
			b = compute(M[i][k], m[k + 1][j], operands[k]);
			c = compute(m[i][k], M[k + 1][j], operands[k]);
			d = compute(m[i][k], m[k + 1][j], operands[k]);
			min = min5(min, a, b, c, d);
			max = max5(max, a, b, c, d);
		}

		long[] result = {0, 0};
		result[0] = min;
		result[1] = max;
		return result;
	}

	private static long compute(long x, long y, char operand) {
		if (operand == '+') {
			return x + y;
		}
		else if (operand == '-') {
			return x - y;
		}
		return x * y;
	}

	private static long max5(long a, long b, long c, long d, long e) {
		return Math.max(Math.max(Math.max(Math.max(a, b), c), d), e);
	}

	private static long min5(long a, long b, long c, long d, long e) {
		return Math.min(Math.min(Math.min(Math.min(a, b), c), d), e);
	}

	// //Generate expression method, for testing
	private static String generateExpression() {
		int length = (int)(Math.random() * 29 + 1); //max length is 29
		while (length < 3 || length % 2 == 0) {
			length = (int)(Math.random() * 29 + 1);
		}

		char[] chars = new char[length];

		char[] operands = {'+', '-', '*'};

		int i, j;
		for (i = 0; i < length; i++) {
			if (i % 2 == 0) {
				j = (int)(Math.random() * 10); //digits 0 through 9
				chars[i] = (char)(j + '0');
			}
			else {
				j = (int)(Math.random() * 3); //0, 1, or 2
				chars[i] = operands[j];
			}
		}

		String expression = new String(chars);
		return expression;
	}

	// //print 2D array method, for testing
    private static void printArr(long[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
