/* Given the schedule of each person in your building, find the most efficient
way to see all of them to get their signature for a petition. This is modeled by
a set of segments. Each segment represents the time slot when a person is at
home. The goal is to select as few points as possible, while still covering all
of the segments. For example:

Input:
4
4 7
1 3
2 5
5 6

Output:
2
3 6

Explanation: Segments 2 and 3 contain the point 3, and segments 1 and 4 contain
the point 6. All four segments cannot be covered by a single point, since [1,3]
and [5,6] are disjoint.

The (greedy) strategy used here was to sort the segments in ascending order by
their endpoints, then select the first endpoint in the array. There are only two
cases: either the segment is disjoint from the others and this point is one of
the optimal points, or the endpoint of the segment overlaps one or more other
segments, in which case the endpoint is needed to cover this segment, and it can
also cover the other segment(s).

The feedback for this solution was:
Good job! (Max time used: 0.21/1.50, max memory used: 26877952/536870912.) */

import java.util.Scanner;

public class CoveringSegments {
    private static class Segment {
        int start, end;
        
        private Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static int[] optimalPoints(Segment[] segments) {
        int n = segments.length;
        sortSegmentsByEndpoint(segments, n);

        int[] points = new int[n];
        int numPoints = 0;

        while (n > 0) {
        	int leftmostEndpoint = segments[0].end;
            points[numPoints] = leftmostEndpoint;
            numPoints++;
            
            for (int j = 0; j < n; j++) {
                if (leftmostEndpoint >= segments[j].start && leftmostEndpoint <= segments[j].end) {
                    Segment temp = segments[n - 1];
                    segments[n - 1] = segments[j];
                    segments[j] = temp;
                    n--;
                    j--;
                }
            }
            sortSegmentsByEndpoint(segments, n);
        }

        int[] result = new int[numPoints];
        System.arraycopy(points, 0, result, 0, numPoints);
        return result;
    }

    private static void sortSegmentsByEndpoint(Segment[] segments, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = segments[i].end;
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (min > segments[j].end) {
                    min = segments[j].end;
                    minIndex = j;
                }
            }
            Segment temp = segments[i];
            segments[i] = segments[minIndex];
            segments[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Segment[] segments = new Segment[input.nextInt()];
        
        for (int i = 0; i < segments.length; i++) {
            segments[i] = new Segment(input.nextInt(), input.nextInt());
        }

        int[] result = optimalPoints(segments);
        System.out.println(result.length);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
