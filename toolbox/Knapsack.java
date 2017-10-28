/* Knapsack (or backpack) without repetition problem, using dynamic 
programming. Given the capacity of the backpack, the number of items (in our
story, the items were gold bars), and an array of the weight of each item,
maximize the value that can fit into the backpack. Each item can only be taken
once, and unlike the fractional knapsack problem in the greedy algorithms
section, it isn't possible to take a fraction of an item.

An example:
Input:
7 4
6 3 4 2
Output:
7

Explanation: the greedy strategy would output a 6, which is incorrect. The value
of the backpack is maximized by skipping over the largest item and taking the
items of weight 3 and 4.

The feedback for this solution was:
Good job! (Max time used: 0.28/2.25, max memory used: 41947136/536870912.) */

import java.util.*;

public class Knapsack {
    private static int maximizeValue(int backpackCapacity, int[] barWeights) {
        int numBars = barWeights.length;
        int[][] value = new int[numBars + 1][backpackCapacity + 1];

        for (int i = 1; i <= numBars; i++) {
            for (int j = 1; j <= backpackCapacity; j++) {
                value[i][j] = value[i - 1][j];
                
                if (barWeights[i - 1] <= j) {
                    int altValue = value[i - 1][j - barWeights[i - 1]] + barWeights[i - 1];
                    if (altValue > value[i][j]) {
                        value[i][j] = altValue;
                    }
                }
            }
        }
        return value[numBars][backpackCapacity];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int backpackCapacity = input.nextInt();
        int numBars = input.nextInt();
        
        int[] barWeights = new int[numBars];
        for (int i = 0; i < numBars; i++) {
            barWeights[i] = input.nextInt();
        }
        System.out.println(maximizeValue(backpackCapacity, barWeights));
    }
}
