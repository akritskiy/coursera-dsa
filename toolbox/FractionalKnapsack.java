/*
Fractional Knapsack: a thief finds more loot than he can carry. Help him find
the most valuable combination of items, assuming that any fraction of an item
can be taken.

Input: first line, number of items and capacity of the knapsack; subsequent
lines, the value and weight of each item. Output: the max value of the knapsack.

For example:
Input:
1 10
500 30
Output:
166.6667
Explanation: we take 1/3 of the only available item.

Input:
3 50
60 20
100 50
120 30
Output:
180.0
Explanation: the value per weight ratios of the items are 3, 2, and 4,
respectively, so we take the first and third item.

The feedback for this solution was:
Good job! (Max time used: 0.31/1.50, max memory used: 35745792/671088640.)
*/

import java.util.Scanner;

public class FractionalKnapsack {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int capacity = input.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = input.nextInt();
            weights[i] = input.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        //create value per weight array
        int n = values.length;
        double[] valuePerWeight = new double[n];
        for (int i = 0; i < n; i++) {
            valuePerWeight[i] = (double)(values[i] / weights[i]);
        }

        int cap = capacity;
        double value = 0;
        
        while (cap > 0) {
            double max = valuePerWeight[0];
            int maxIndex = 0;
            for (int i = 1; i < n; i++) {
                if (valuePerWeight[i] > max) {
                    max = valuePerWeight[i];
                    maxIndex = i;
                }
            }
            valuePerWeight[maxIndex] = 0;

            if (max == 0) {
                return value;
            }
            else if (cap >= weights[maxIndex]) {
                value += (double)values[maxIndex];
                cap -= weights[maxIndex];
            }
            else {
                value += max * (double)cap;
                cap = 0;
            }
        }
        return value;
    }
}
