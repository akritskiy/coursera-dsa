/* Standard binary search algorithm. The feedback for this solution was:
Good job! (Max time used: 1.99/3.00, max memory used: 194584576/536870912.) */

import java.util.Scanner;

public class BinarySearch {
    private static int binarySearch(int[] array, int key) {
        return binarySearch(array, key, 0, array.length - 1);
    }
    
    private static int binarySearch(int[] array, int key, int low, int high) {
        int mid = (low + high) / 2;
        
        if (array[mid] == key) {
            return mid;
        }
        if (high <= low) {
            return -1;
        }
        if (array[mid] < key) {
            return binarySearch(array, key, mid + 1, high);
        }        
        if (array[mid] > key) {
            return binarySearch(array, key, low, mid - 1);
        }
        return -1; // if not found
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[] array = new int[input.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = input.nextInt();
        }
        
        int numKeys = input.nextInt();
        for (int i = 0; i < numKeys; i++) {
            System.out.print(binarySearch(array, input.nextInt()) + " ");
        }
    }
}