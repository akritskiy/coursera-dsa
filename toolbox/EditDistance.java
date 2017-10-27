/* The edit distance between two strings is the number of insertions, deletions,
and mismatches in an alignment of two strings. Implement a dynamic programming
algorithm for computing edit distance.

Example: The editing distance of two strings, "editing" and "distance"...

e | d | i | - | t | i | n | g | -
- | d | i | s | t | a | n | c | e

There are 3 insertions/deletions (indicated by a "-") and 2 mismatches. The output: 5

The feedback for this solution was:
Good job! (Max time used: 0.18/1.50, max memory used: 26275840/536870912.) */

import java.util.Scanner;

public class EditDistance {
	private static int editDistance(String a, String b) {
		int rows = a.length() + 1;
		int cols = b.length() + 1;
		int[][] distance = new int[rows][cols];

		int i, j;
		for (i = 0; i < rows; i++) {
			distance[i][0] = i;
		}
		for (j = 0; j < cols; j++) {
			distance[0][j] = j;
		}
		
		for (j = 1; j < cols; j++) {
			for (i = 1; i < rows; i++) {
				int insertion = distance[i][j - 1] + 1;
				int deletion = distance[i - 1][j] + 1;

				int mismatch = Integer.MAX_VALUE;
				if (a.charAt(i - 1) != b.charAt(j - 1)) {
					mismatch = distance[i - 1][j - 1] + 1;
				}

				int match = Integer.MAX_VALUE;
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					match = distance[i - 1][j - 1];
				}
				distance[i][j] = minFour(insertion, deletion, mismatch, match);
			}
		}
		return distance[rows - 1][cols - 1];
	}

	private static int minFour(int a, int b, int c, int d) {
		return Math.min(Math.min(Math.min(a, b), c), d);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(editDistance(input.next(), input.next()));
	}
}
