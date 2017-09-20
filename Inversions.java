//The goal of this problem is to count the number of inversions of a given sequence.
//For example, an array sorted in ascending order contains no inversions, while in an
//array sorted in descending order, any two elements constitute an inversion, for a
//total of n * (n - 1) / 2 inversions.

//The solution is a modification of the merge sort algorithm.

//The feedback for this solution was: Good job! (Max time used: 0.99/4.50, max memory used: 137515008/536870912.)

import java.util.*;

public class Inversions {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = input.nextInt();
		}
		Data x = mergeSort(a);
		System.out.println(x.count);

		// //SIMPLE TEST for counting inversions.
		// //Use an array sorted in descending order. The number of inversions should be n * (n - 1) / 2.
		// int[] a = {6, 5, 4, 3, 2, 1};
		// Data x = mergeSort(a);
		// System.out.println(Arrays.toString(x.array));
		// System.out.println(x.count);
		// int n = a.length;
		// int numInversions = n * (n - 1) / 2;
		// if (numInversions == x.count) {
		// 	System.out.println("Correct");
		// }
		// else {
		// 	System.out.println("Wrong");
		// }
		// //END SIMPLE TEST

		// //STRESS TEST for sorting function. Does not test counting of inversions.
		// while (true) {
		// 	int n = (int)(Math.random() * 10 + 1);
		// 	int[] a = new int[n];
		// 	for (int i = 0; i < n; i++) {
		// 		a[i] = (int)(Math.random() * 1000000000 + 1);
		// 	}
			
		// 	//use mergeSort
		// 	Data x = mergeSort(a);
		// 	int[] b = x.array;
			
		// 	//sort a using Arrays.sort
		// 	Arrays.sort(a);
			
		// 	System.out.println("Array a: " + Arrays.toString(a));
		// 	System.out.println("Array b: " + Arrays.toString(b));

		// 	if (identical(a, b)) {
		// 		System.out.println("Correct");
		// 	}
		// 	else {
		// 		System.out.println("Wrong");
		// 		break;
		// 	}
		// }
		// //END STRESS TEST
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

	//A datatype to contain an array and the inversion count.
	private static class Data {
		private int[] array = {};
		private long count = 0;

		private Data() {}

		private Data(int[] array, long count) {
			this.array = array;
			this.count = count;
		}

		private Data(int arrayLength, long count) {
			this.array = new int[arrayLength];
			this.count = count;
		}
	}

	//Identical method: compares two arrays... returns true if identical, false otherwise.
	//Used to test the sorting function of mergeSort.
	private static boolean identical(int[] a, int[] b) {
		boolean verdict = true;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				verdict = false;
				break;
			}
		}
		return verdict;
	}
}
