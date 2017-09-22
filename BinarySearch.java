//Simple binary search algorithm. The feedback for this solution was:
//Good job! (Max time used: 1.62/3.00, max memory used: 197115904/536870912.)

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //Input array
        int n = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }
        
        //Input keys array
        int m = input.nextInt();
        int[] keys = new int[m];
        for (int i = 0; i < m; i++) {
            keys[i] = input.nextInt();
        }
        
        //Output
        for (int i = 0; i < m; i++) {
            System.out.print(binarySearch(array, keys[i]) + " ");
        }
    }
    
    private static int binarySearch(int[] array, int key) {
        return binarySearch(array, key, 0, array.length - 1);
    }
    
    private static int binarySearch(int[] array, int key, int low, int high) {
        int mid = (low + high) / 2;
        
        if (array[mid] == key) {
            return mid;
        }
        else if (high <= low) {
            return -1;
        }
        else if (array[mid] < key) {
            return binarySearch(array, key, mid + 1, high);
        }
        else if (array[mid] > key) {
            return binarySearch(array, key, low, mid - 1);
        }
        //If not found...
        return -1;
    }
}
