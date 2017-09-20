//Given an array of n elements, determine if the array contains an element that occurs more than n/2 times.
//Design a divide-and-conquer O(nlog(n)) algorithm.

//The feedback for this solution was: Good job! (Max time used: 0.98/1.50, max memory used: 118337536/536870912.)

import java.util.*;

public class MajorityElement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        
        int result = finalCheck(arr);        
        if (result == -1) {
            System.out.println(0); //if there isn't a majority element, print 0
        }
        else {
            System.out.println(1); //if there is a majority element, print 1
        }

        //STRESS TEST
        // while (true) {
        //     int n = (int)(Math.random() * 15 + 1);
        //     int[] arr = new int[n];
        //     for (int i = 0; i < n; i++) {
        //         arr[i] = (int)(Math.random() * 3 + 1);
        //     }
        //     int naiveResult = naive(arr);
        //     int result = finalCheck(arr);
        //     if (naiveResult == result) {
        //         System.out.println("Correct");
        //     }
        //     else {
        //         System.out.println("Wrong");
        //         System.out.println("Naive result: " + naiveResult);
        //         System.out.println("Result: " + result);
        //         System.out.println(Arrays.toString(arr));
        //         Arrays.sort(arr);
        //         System.out.println(Arrays.toString(arr));
        //         break;
        //     }
        // }
    }

    //FINAL CHECK METHOD
    private static int finalCheck(int[] arr) {
        int key = major(arr);
        int count = 0;
        int n = arr.length;
        int mid = n / 2;
        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                count++;
                if (count > mid) {
                    return key;
                }
            }
        }
        return -1;
    }

    //MAJOR METHOD
    private static int major(int[] arr) {
        return major(arr, 0, arr.length - 1);
    }

    private static int major(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        if (right - left == 1) {
            if (arr[left] == arr[right]) {
                return arr[left];
            }
            return -1;
        }

        int mid = (right + left) / 2;
        int leftHalf = major(arr, left, mid);
        int rightHalf = major(arr, mid + 1, right);

        if (leftHalf == rightHalf) {
            return leftHalf;
        }

        if (leftHalf != -1 && rightHalf != -1) {
            int l = 0;
            int r = 0;
            for (int i = left; i <= right; i++) {
                if (arr[i] == leftHalf) {
                    l++;
                }
                else if (arr[i] == rightHalf) {
                    r++;
                }
            }
            if (r > l) {
                return rightHalf;
            }
            else if (l > r) {
                return leftHalf;
            }
            else {
                l = 0;
                r = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == leftHalf) {
                        l++;
                    }
                    if (arr[i] == rightHalf) {
                        r++;
                    }
                }
                if (l > arr.length / 2) {
                    return leftHalf;
                }
                if (r > arr.length / 2) {
                    return rightHalf;
                }
                else {
                    return -1;
                }
            }
        }

        if (leftHalf != -1) {
            int c = 0;
            for (int i = left; i <= right; i++) {
                if (arr[i] == leftHalf) {
                    c++;
                    if (c > (right - left) / 2) {
                        return leftHalf;
                    }
                }
            }
        }

        if (rightHalf != -1) {
            int c = 0;
            for (int i = left; i <= right; i++) {
                if (arr[i] == rightHalf) {
                    c++;
                    if (c > (right - left) / 2) {
                        return rightHalf;
                    }
                }
            }
        }

        return -1;
    }

    //NAIVE METHOD
    // private static int naive(int[] arr) {
    //     int n = arr.length;
    //     int mid = n / 2;

    //     int[] copy = new int[n];
    //     System.arraycopy(arr, 0, copy, 0, n);
    //     Arrays.sort(copy);
    
    //     int key = copy[mid];
    //     int count = 0;
    //     for (int i = 0; i < n; i++) {
    //         if (copy[i] == key) {
    //             count++;
    //             if (count > mid) {
    //                 return key;
    //             }
    //         }
    //     }
    //     return -1;
    // }
}
