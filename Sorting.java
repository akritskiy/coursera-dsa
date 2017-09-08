//Design a quick sort algorithm to efficiently process arrays with few unique elements.

//Begin with a naive quick sort algorithm, using a two-way partition. At best (with balanced partitions), the running time
//of this algorithm is O(nlogn). At worst, e.g. if all of the elements in the array are the same element, the running time is O(n^2).
//Implement a partition method that separates elements into three partitions: less than, equal to, and greater than pivot.

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

        // //TEST
        // int n = (int)(Math.random() * 20 + 1);
        // int[] a = new int[n];
        // for (int i = 0; i < n; i++) {
        //     a[i] = (int)(Math.random() * 10 + 1);
        // }
        
        // int[] copy = new int[a.length];
        // System.arraycopy(a, 0, copy, 0, a.length);

        // System.out.println("Before: ");
        // System.out.println(Arrays.toString(a));        
        // System.out.println("After: ");
        // System.out.println(Arrays.toString(quickSort(a)));        
        // Arrays.sort(copy);
        // System.out.println("Copy, sorted by Arrays.sort: ");
        // System.out.println(Arrays.toString(copy));
        // //END TEST
    }
    
    //QUICK SORT METHOD
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
    //END QUICK SORT METHOD
    
    //THREE-WAY PARTITION METHOD
    private static int[] partition(int[] arr) {
        return partition(arr, 0, arr.length - 1);
    }

    private static int[] partition(int[] arr, int left, int right) {
        int[] resultArr = {0, 0};

        //Randomize pivot...
        int k = (int)(Math.random() * (right - left + 1) + left);
        int temp = arr[left];
        arr[left] = arr[k];
        arr[k] = temp;
        int pivot = arr[left];

        int n = right; //n = the rightmost index... used to keep track of the equal-to partition
        int j = left + 1;
        for (int i = left + 1; i <= n; i++) {
            if (arr[i] < pivot) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
            else if (arr[i] == pivot) {
                temp = arr[n];
                arr[n] = arr[i];
                arr[i] = temp;
                n--;
                i--;
            }
        }
        j--;

        if (j == 0) {
            resultArr[0] = 0;
        }
        else {
            resultArr[0] = j - 1;
        }
        //resultArr[0] = the rightmost index of the less-than partition

        temp = arr[left];
        arr[left] = arr[j];
        arr[j] = temp;

        j++;
        for (int i = right; i > n; i--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            j++;
        }

        resultArr[1] = j; //the leftmost index of the greater-than partition
        return resultArr;
    }
    //END THREE-WAY PARTITION METHOD
}
