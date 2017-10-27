/* The goal of this problem is to count the number of inversions of a given
sequence. An "inversion" is any two elements in an array such that array[i] >
array[i + 1]. For example, the array {2, 1} contains one inversion. If the
inversion is performed, the resulting array {1, 2} is sorted (in ascending
order). The array {3, 1, 2} contains two inversions. An array that is already
sorted contains zero inversions, and an array sorted in descending order
contains n * (n - 1) / 2 inversions.

Array length can be as large as 10^5, and each ai can be as large as 10^9. In
the worst case, the number of inversions can be 4,999,950,000, so the number of
inversions should be stored in a long datatype. An example:

Input:
5
2 3 9 2 9
Output:
2
Explanation (0-based numbering):
Invert element 2 and 3 = 2 3 2 9 9. Invert element 1 and 2 = 2 2 3 9 9.

The solution is a modification of merge sort. Note: the pseudocode for merge
sort was given in the lecture. The feedback for this solution was:
Good job! (Max time used: 0.99/4.50, max memory used: 137515008/536870912.) */

import java.util.Scanner;

public class Inversions {
	private static class Data {
		private int[] array = {};
		private long count = 0;

		private Data() {}

		private Data(int arrayLength, long count) {
			this.array = new int[arrayLength];
			this.count = count;
		}
	}

	private static Data mergeSort(int[] arr) {
		return mergeSort(arr, 0, arr.length - 1);
	}

	private static Data mergeSort(int[] arr, int left, int right) {
		Data result = new Data();

		if (left == right) {
			result.array = new int[1];
			result.array[0] = arr[left];
			return result;
		}

		int mid = (right + left) / 2;

		Data leftHalf = mergeSort(arr, left, mid);
		int[] leftArr = leftHalf.array;
		result.count += leftHalf.count;

		Data rightHalf = mergeSort(arr, mid + 1, right);
		int[] rightArr = rightHalf.array;
		result.count += rightHalf.count;

		Data merged = merge(leftArr, rightArr);
		result.array = merged.array;
		result.count += merged.count;

		return result;
	}

	private static Data merge(int[] a, int[] b) {
		Data data = new Data(a.length + b.length, 0);

		int i = 0, j = 0, k = 0;
		while (true) {
			if (a[i] < b[j]) {
				data.array[k] = a[i];
				i++;
				k++;
			}
			else if (a[i] > b[j]) {
				data.count += a.length - i;
				data.array[k] = b[j];
				j++;
				k++;
			}
			else if (a[i] == b[j]) {
				data.array[k] = a[i];
				i++;
				k++;
			}

			if (i == a.length) {
				while (j < b.length) {
					data.array[k] = b[j];
					j++;
					k++;
				}
				return data;
			}

			if (j == b.length) {
				while (i < a.length) {
					data.array[k] = a[i];
					i++;
					k++;
				}
				return data;
			}
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] array = new int[input.nextInt()];

		for (int i = 0; i < array.length; i++) {
			array[i] = input.nextInt();
		}
		System.out.println(mergeSort(array).count);
	}
}
