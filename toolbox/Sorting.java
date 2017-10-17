/*
Design a quick sort algorithm to efficiently process arrays with few unique
elements. Quick sort, at best (with balanced partitions), runs in O(nlogn) time;
at worst (e.g. if all of the elements in the array are the same element), it
runs in O(n^2) time. Implement a partition method that separates elements into
three partitions: less than, equal to, and greater than the pivot.

The feedback for this solution was:
Good job! (Max time used: 1.72/5.50, max memory used: 136478720/536870912.)
*/

import java.util.*;

public class Sorting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        
        quickSort(arr);
        
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        /* // Test
        int n = (int)(Math.random() * 20 + 1);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int)(Math.random() * 10 + 1);
        }
        
        int[] copy = new int[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        System.out.println("Before: ");
        System.out.println(Arrays.toString(a));        
        System.out.println("After: ");
        System.out.println(Arrays.toString(quickSort(a)));        
        Arrays.sort(copy);
        System.out.println("Copy, sorted by Arrays.sort: ");
        System.out.println(Arrays.toString(copy));
        // End test */
    }
    
    private static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }
    
    private static int[] quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return arr;
        }

        int[] partitionIndexes = partition(arr, left, right);
        quickSort(arr, left, partitionIndexes[0]);
        quickSort(arr, partitionIndexes[1], right);
        return arr;
    }
    
    private static int[] partition(int[] arr) {
        return partition(arr, 0, arr.length - 1);
    }

    private static int[] partition(int[] arr, int left, int right) {
        int[] resultArr = new int[2]; // initialize result array

        int r = (int)(Math.random() * (right - left + 1) + left); 
        int temp = arr[left];
        arr[left] = arr[r];
        arr[r] = temp; // randomize pivot
        
        int pivot = arr[left];
        int k = right;
        int j = left + 1;
        for (int i = left + 1; i <= k; i++) {
            if (arr[i] < pivot) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
            else if (arr[i] > pivot) {
                temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
                k--;
                i--;
            }
        }
        j--;
        k++;
        
        temp = arr[left];
        arr[left] = arr[j];
        arr[j] = temp;
        
        if (j <= 0) {
            resultArr[0] = 0;
        }
        else {
            resultArr[0] = j - 1;
        } // resultArr[0] is set to the rightmost index of the less-than partition
        
        if (k >= right) {
            resultArr[1] = right;
        }
        else {
            resultArr[1] = k;
        } // resultArr[1] is set to the leftmost index of the greater-than partition
        
        return resultArr;
    }
}
