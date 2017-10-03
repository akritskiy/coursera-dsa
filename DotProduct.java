/*
Maximize Dot Product:
Given n ads to place on a popular internet page, an array of the profit per click of each ad, and the average
number of clicks per day of each ad slot, partition them into pairs such that the ad revenue is maximized.

The feedback for this solution was: Good job! (Max time used: 0.32/1.50, max memory used: 35250176/536870912.)
*/

import java.util.*;

public class DotProduct {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }

        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = input.nextInt();
        }

        System.out.println(maxDotProduct(a, b));
    }

    private static long maxDotProduct(int[] a, int[] b) {
        int[] x = sortArr(a);
        int[] y = sortArr(b);

        long result = 0;
        for (int i = 0; i < x.length; i++) {
            result += (long)(x[i] * y[i]);
        }
        return result;
    }

    //returns array sorted greatest to least, using selection sort
    private static int[] sortArr(int[] arr) {
        int n = arr.length;
        for (int j = 0; j < n - 1; j++) {
            int max = arr[j];
            int maxIndex = j;

            for (int i = j + 1; i < n; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                    maxIndex = i;
                }
            }

            int temp = max;
            arr[maxIndex] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}
