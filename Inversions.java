//The goal of this problem is to count the number of inversions of a given sequence.
//An inversion of a sequence a0, a1, ... , an is a pair of indices 0 <= i < j <= n
//such that ai > aj. The number of inversions in some sense measures how close the
//sequence is to being sorted. For example, the array {2, 1} contains one inversion,
//and the array {3, 1, 2} contains two inversions. An array that is already sorted
//in ascending order contains zero inversions, and an array sorted in descending order
//contains n * (n - 1) / 2 inversions.

//The constraints: n can be as large as 10^5, and each ai can be as large as 10^9.
//In the worst case, the number of inversions could be 4,999,950,000. Therefore, the
//number of inversions must be stored in a long datatype.

//The solution is a modification of the merge sort algorithm. The feedback for this solution was:
//Max time used: 0.99/4.50, max memory used: 137515008/536870912.

import java.util.*;

public class PointsAndSegments {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int s = input.nextInt(); //Number of segments
		int[] starts = new int[s];
		int[] ends = new int[s];
		int p = input.nextInt(); //Number of points
		int[] points = new int[p];
		
		for (int i = 0; i < s; i++) {
			starts[i] = input.nextInt();
			ends[i] = input.nextInt();
		}

		for (int i = 0; i < p; i++) {
			points[i] = input.nextInt();
		}

		int[] result = count(starts, ends, points);
		for (int i = 0; i < p; i++) {
			System.out.print(result[i] + " ");
		}
	}

	private static int[] count(int[] starts, int[] ends, int[] points) {
		int s = starts.length; 	//Number of segments
		int p = points.length; 	//Number of points
		int n = 2 * s + p; 		//Number of point-label pairs

		Pair[] pairs = new Pair[n];
		int j = 0;

		//the pairs will be labeled 1 for start, 2 for point, 3 for end
		for (int i = 0; i < s; i++) {
			pairs[j] = new Pair(starts[i], 1);
			j++;
			pairs[j] = new Pair(ends[i], 3);
			j++;
		}

		for (int i = 0; i < p; i++) {
			pairs[j] = new Pair(points[i], 2);
			j++;
		}
		//At this point, pairs contains all the point-label pairs

		quickSort(pairs);

		//Need a new arr to store each point in points, and the count for that point... point-count pairs
		Pair[] counts = new Pair[p];
		int k = 0;
		int leftCount = 0;
		int rightCount = 0;

		for (int i = 0; i < n; i++) {
			if (pairs[i].label == 1) {
				leftCount++;
			}
			else if (pairs[i].label == 3) {
				rightCount++;
			}
			else if (pairs[i].label == 2) {
				int point = pairs[i].point;
				int count = leftCount - rightCount;
				counts[k] = new Pair(point, 0, count);
				k++;
			}
		}
		//At this point, counts array contains the point-count pairs

		//This will map the count for each point back to its index in the points array
		int[] result = new int[p];
		for (int i = 0; i < p; i++) {
			for (int m = 0; m < p; m++) {
				if (points[i] == counts[m].point) {
					result[i] = counts[m].count;
				}
			}
		}

		return result;
	}

	//Begin sort method
	private static Pair[] quickSort(Pair[] arr) {
		return quickSort(arr, 0, arr.length - 1);
	}

	private static Pair[] quickSort(Pair[] arr, int left, int right) {
		if (left >= right) {
			return arr;
		}

		int[] m = partition(arr, left, right);
		quickSort(arr, left, m[0]);
		quickSort(arr, m[1], right);
		return arr;
	}

	private static int[] partition(Pair[] arr, int left, int right) {
		int[] result = {0, 0}; //initialize result arr

		//randomize pivot
		int random = (int)(Math.random() * (right - left + 1) + left);
		Pair temp = arr[left];
		arr[left] = arr[random];
		arr[random] = temp;

        //sort
		Pair pivot = arr[left];
		int k = right;
		int j = left + 1;
		for (int i = left + 1; i <= k; i++) {
			if (arr[i].point < pivot.point) {
				temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				j++;
			}
			else if (arr[i].point == pivot.point) {
				if (arr[i].label < pivot.label) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
					j++;
				}
				else if (arr[i].label > pivot.label) {
					temp = arr[k];
					arr[k] = arr[i];
					arr[i] = temp;
					k--;
					i--;
				}
			}
			else if (arr[i].point > pivot.point) {
				temp = arr[k];
				arr[k] = arr[i];
				arr[i] = temp;
				k--;
				i--;
			}
		}
		j--;
		k++;

        //move pivot to its final place within the array
		temp = arr[left];
		arr[left] = arr[j];
		arr[j] = temp;

        //store and return the indices of the equals partition
		if (j <= 0) {
			result[0] = 0;
		}
		else {
			result[0] = j - 1;
		}

		if (k >= right) {
			result[1] = right;
		}
		else {
			result[1] = k;
		}

		return result;
	}
	//End sort method

	//point-label pair
	private static class Pair {
		private int point, label, count;

		private Pair(int point, int label) {
			this.point = point;
			this.label = label;
		}

		private Pair(int point, int label, int count) {
			this.point = point;
			this.label = label;
			this.count = count;
		}
	}

	//print pair[] method, for testing
	private static void printPairArr(Pair[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			System.out.println("Index: " + i + ", point, label: " + arr[i].point + ", " + arr[i].label);
		}
	}
}
