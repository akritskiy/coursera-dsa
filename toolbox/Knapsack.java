/*
Knapsack (or backpack) without repetition problem. Given W = the capacity of the
backpack, n = the number of items (in our story, the items were gold bars), and
an array w = the weight of each item, maximize the value that can fit into the
backpack. Each item can only be taken once, and it isn't possible to take a
fraction of an item.

For example:
Input:
7 4
6 3 4 2
Output:
7
Explanation: the greedy strategy would output a 6, which is incorrect... The
value of the backpack is maximized by skipping over the largest item and taking
the items of weight 3 and 4.

The feedback for this solution was:
Good job! (Max time used: 0.28/2.25, max memory used: 41947136/536870912.)
*/

import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int W, n;
        W = input.nextInt();
        n = input.nextInt();

        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = input.nextInt();
        }

        System.out.println(maximizeValue(W, w));
    }

    private static int maximizeValue(int W, int[] w) {
        int n = w.length; // number of bars

        int[][] value = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) { // j = current capacity of the backpack
                value[i][j] = value[i - 1][j];
                
                if (w[i - 1] <= j) {
                    int val = value[i - 1][j - w[i - 1]] + w[i - 1];
                    if (val > value[i][j]) {
                        value[i][j] = val;
                    }
                }
            }
        }
        return value[n][W];
    }

    /* // print 2D array method, useful for testing
    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    } */
}
