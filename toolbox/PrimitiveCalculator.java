/*
Dynamic programming problem: given an integer n and a primitive calculator that can do three operations (+1, *2, *3),
compute the minimum number of operations needed to obtain n starting from 1. Then, reconstruct the sequence of the
number's path from  1 to n. For example:

Input: 5
Output:
3
1 2 4 5
Explanation: the optimal path from 1 to 5 takes 3 operations: 1 * 2 * 2 + 1.

Input: 10
Output:
3
1 3 9 10
Explanation: the optimal path from 1 to 10 also takes 3 operations: 1 * 3 * 3 + 1.

The feedback for this solution was: Good job! (Max time used: 0.28/2.25, max memory used: 42102784/536870912.)
*/

import java.util.*;

public class PrimitiveCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        ArrayList<Integer> arr = minOperations(n);
        System.out.println(arr.get(n));

        int[] sequence = getSequence(arr, n);
        for (int i = 0; i < sequence.length; i++) {
            System.out.print(sequence[i] + " ");
        }
    }

    private static int[] getSequence(ArrayList<Integer> arr, int n) {
        int key = n;
        int j = arr.get(n); //number of steps

        int[] sequence = new int[j + 1];
        sequence[0] = 1;
        sequence[j] = key;

        for (int i = n; i >= 0; i--) {
            if (arr.get(i) == j - 1) {

                if (key - 1 == i) {
                    key--;
                    sequence[j - 1] = key;
                    j--;
                }
                else if (key % 2 == 0 && key / 2 == i) {
                    key = key / 2;
                    sequence[j - 1] = key;
                    j--;
                }
                else if (key % 3 == 0 && key / 3 == i) {
                    key = key / 3;
                    sequence[j - 1] = key;
                    j--;
                }

            }
        }
        return sequence;
    }

    private static ArrayList<Integer> minOperations(int n) {
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for (int i = 0; i < 2; i++) {
            arr.add(0);
        }

        for (int i = 2; i <= n; i++) {
            int x, y, z;
            x = arr.get(i - 1);
            y = Integer.MAX_VALUE;
            z = Integer.MAX_VALUE;

            if (i % 2 == 0) {
                y = arr.get(i / 2);
            }
            if (i % 3 == 0) {
                z = arr.get(i / 3);
            }

            int min = Math.min(Math.min(x, y), z);
            arr.add(i, min + 1);
        }

        return arr;
    }
}
