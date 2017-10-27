/* Given an array of n elements, determine if the array contains an element that
occurs more than n/2 times. Design a divide-and-conquer O(nlogn) algorithm.

The desired output is either a 1, indicating that there is a majority element,
or a 0, indicating that there isn't a majority element. For example:

Input:
5
2 3 9 2 2
Output:
1

Input:
4
1 2 3 4
Output:
0

The feedback for this solution was:
Good job! (Max time used: 0.98/1.50, max memory used: 118337536/536870912.) */

import java.util.Scanner;

public class MajorityElement {
    private static int finalCheckMajority(int[] arr) {
        int candidate = majorityCandidate(arr, 0, arr.length - 1);
        int count = 0;
        int mid = arr.length / 2;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candidate) {
                count++;
                if (count > mid) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int majorityCandidate(int[] arr, int left, int right) {
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
        int leftHalf = majorityCandidate(arr, left, mid);
        int rightHalf = majorityCandidate(arr, mid + 1, right);

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
                return -1;
            }
        }

        if (leftHalf != -1) {
            int l = 0;
            for (int i = left; i <= right; i++) {
                if (arr[i] == leftHalf) {
                    l++;
                    if (l > (right - left) / 2) {
                        return leftHalf;
                    }
                }
            }
        }

        if (rightHalf != -1) {
            int r = 0;
            for (int i = left; i <= right; i++) {
                if (arr[i] == rightHalf) {
                    r++;
                    if (r > (right - left) / 2) {
                        return rightHalf;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = new int[input.nextInt()];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = input.nextInt();
        }
        System.out.println(finalCheckMajority(arr));
    }
}
