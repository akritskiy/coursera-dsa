/*
Given a set of points and a set of segments, the goal is to compute, for each point, the number of
segments that contain this point.

The number of segments {(a0, b0), (a1, b1), ..., (as-1, bs-1)} and points {x0, x1, ..., xp-1} can
each be as large as 50,000. Each point and segment endpoint can range from -10^8 to 10^8.

Output p integers {k0, k1, ..., kp-1} where each ki is the number of segments which contain xi.

For example:
1. Given segments (0, 5) and (7, 10) and points {1, 6, 11}, the output is 1 0 0.
Explanation: The first point, 1, is found once, in the 1st segment. The others, 6 and 11, are
not found in either of the segments.

2. Given segment (-10, 10) and points {-100, 100, 0}, the output is 0 0 1.

3. Given segments {(0, 5), (-3, 2), (7, 10)} and points {1, 6}, the output is 2 0.

The strategy used here was to label each start with a 1, each end with a 3, and each point with a 2.
These point-label pairs were sorted first by the point, and where the point values were equal, they
were sorted by the label. Take example 2 from above... The array of point-label pairs is
{ (-10, 1), (10, 3), (-100, 2), (100, 2), (0, 2) } ... and when sorted:
{ (-100, 2), (-10, 1), (0, 2), (10, 3), (100, 2) }. We then iterate through this array. Each time we
see the label 1, we increment the start count... label 3, increment end count, and label 2... we save
a point-count pair in a new array. The count for that point will be (start count - end count). All
that remains is to map the count for each point back to the correct index in the original points array.

The feedback for this solution was: "Good job!" and Max time used: 4.88/6.00, max memory used: 168296448/536870912.
*/

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
		int s = starts.length; //Number of segments
		int p = points.length; //Number of points
		int n = 2 * s + p; //Number of point-label pairs

		Pair[] pairs = new Pair[n];
		int j = 0;

		//Each point will get a label: 1 for start, 2 for point, 3 for end
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

	//print Pair[] method, for testing
	private static void printPairArr(Pair[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			System.out.println("Index: " + i + ", point, label: " + arr[i].point + ", " + arr[i].label);
		}
	}
}
