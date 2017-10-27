/* Given a set of points and a set of segments, the goal is to compute, for each
point, the number of segments that contain the point. The number of segments and
points can each be as large as 50,000. Each point and segment endpoint can range
from -10^8 to 10^8.

For example:
Input:
2 3
0 5
7 10
1 6 11
Output:
1 0 0
Explanation: we have 2 segments (0, 5) and (7, 10), and 3 points {1, 6, 11}. The
first point, 1, is found once, in the first segment. The others, 6 and 11, are
not found in either of the segments.

Input:
1 3
-10 10
-100 100 0
Output:
0 0 1
Explanation: we have 1 segment (-10, 10) and 3 points {-100, 100, 0}. Only the
third point, 0, is found on the segment.

The strategy is to label the start and end of each segment with a 1 and 3,
respectively, and each point with a 2. These point-label pairs are then sorted
by the point, and where the point values are equal, they are sorted by the
label. We then iterate through this array. Each time we see label = 1, we
increment the start count; label = 3, increment the end count; and label = 2, we
save a point-count pair in a new array. The count for that point is the current
start count - end count. The counts are then mapped back to the appropriate
position in the original points array.

The feedback for this solution was:
Good job! (Max time used: 4.88/6.00, max memory used: 168296448/536870912.)

27 Oct 2017: rewrote most of the solution, tried to adopt "best practice" of
having shorter methods that do only one thing, more descriptive var names, etc.
The result:
Good job! (Max time used: 3.27/6.00, max memory used: 168620032/536870912.)
... 67% of the previous max time used. */

import java.util.*;

public class PointsAndSegments {
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

	private static Pair[] labelPoints(int[] starts, int[] ends, int[] points) {
		int s = starts.length;
		int p = points.length;
		Pair[] labeledPoints = new Pair[2 * s + p];
		int j = 0;

		for (int i = 0; i < s; i++) {
			labeledPoints[j] = new Pair(starts[i], 1);
			j++;
			labeledPoints[j] = new Pair(ends[i], 3);
			j++;
		}

		for (int i = 0; i < p; i++) {
			labeledPoints[j] = new Pair(points[i], 2);
			j++;
		}
		return labeledPoints;
	}

	private static Pair[] getCounts(Pair[] labeledPoints, int numPoints) {
		Pair[] counts = new Pair[numPoints];
		int j = 0;
		int startCount = 0;
		int endCount = 0;

		for (int i = 0; i < labeledPoints.length; i++) {
			if (labeledPoints[i].label == 1) {
				startCount++;
			}
			else if (labeledPoints[i].label == 3) {
				endCount++;
			}
			else if (labeledPoints[i].label == 2) {
				counts[j] = new Pair(labeledPoints[i].point, 0, startCount - endCount);
				j++;
			}
		}
		return counts;
	}

	private static void printResult(Pair[] counts, int[] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (points[i] == counts[j].point) {
					System.out.print(counts[j].count + " ");
					break;
				}
			}
		}
	}

	private static void quickSort(Pair[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		int[] partitionIndices = partition(arr, left, right);
		quickSort(arr, left, partitionIndices[0]);
		quickSort(arr, partitionIndices[1], right);
	}

	private static void randomizePivot(Pair[] array, int left, int right) {
        int randomIndex = (int)(Math.random() * (right - left + 1) + left);
        
        Pair temp = array[left];
        array[left] = array[randomIndex];
        array[randomIndex] = temp;
    }

	private static int[] partition(Pair[] arr, int left, int right) {
		randomizePivot(arr, left, right);

		Pair pivot = arr[left];
		int k = right;
		int j = left + 1;
		Pair temp;

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
		temp = arr[left];
		arr[left] = arr[j - 1];
		arr[j - 1] = temp;

		int[] partitionIndices = {0, 0};
		partitionIndices[0] = Math.max(0, j - 2); // rightmost index of the less-than partition
		partitionIndices[1] = Math.min(right, k + 1); // leftmost index of the greater-than partition
		return partitionIndices;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int s = input.nextInt();
		int p = input.nextInt();

		int[] starts = new int[s];
		int[] ends = new int[s];
		for (int i = 0; i < s; i++) {
			starts[i] = input.nextInt();
			ends[i] = input.nextInt();
		}

		int[] points = new int[p];
		for (int i = 0; i < p; i++) {
			points[i] = input.nextInt();
		}

		Pair[] labeledPoints = labelPoints(starts, ends, points);
		quickSort(labeledPoints, 0, labeledPoints.length - 1);
		Pair[] counts = getCounts(labeledPoints, points.length);
		printResult(counts, points);
	}
}
