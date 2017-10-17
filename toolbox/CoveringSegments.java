/*
Given the schedule of each person in your building, find the most efficient way
to see all of them to get their signature for a petition. This is modeled by a
set of segments. Each segment represents the time slot when a person is at home.
The goal is to select as few points as possible, while still covering all of the
segments.

The feedback for this solution was:
Good job! (Max time used: 0.21/1.50, max memory used: 26877952/536870912.)
*/

import java.util.*;

public class CoveringSegments {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Segment[] segments = new Segment[n];
        
        //Populate segments array
        for (int i = 0; i < n; i++) {
            int start, end;
            start = input.nextInt();
            end = input.nextInt();
            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }
            segments[i] = new Segment(start, end);
        }

        //Invoke optimalPoints method, store the result in an array
        int[] result = optimalPoints(segments);
        int m = result.length;
        
        //Output
        System.out.println(m);
        for (int i = 0; i < m; i++) {
            System.out.print(result[i] + " ");
        }
    }

    //Optimal points method
    private static int[] optimalPoints(Segment[] segments) {
        int n = segments.length;
        sortSegments(segments, n);

        int[] points = new int[n];
        int numPoints = 0;

        while (n > 0) {
        	//Select the end point of the left-most segment (if viewed on a
            //number line, 0...1...2...etc.)
            int point = segments[0].end;
            points[numPoints] = point;
            numPoints++;
            
            //Check segments
            for (int j = 0; j < n; j++) {
                if (point >= segments[j].start && point <= segments[j].end) {
                    Segment temp = segments[n-1];
                    segments[n-1] = segments[j];
                    segments[j] = temp;
                    n--;
                    j--;
                }
            }
            sortSegments(segments, n);
        }

        //Clean up the points array
        int[] result = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            result[i] = points[i];
        }
        return result;
    }

    //Sort the segments array by end-point. Ascending. Uses selection-sort.
    private static void sortSegments(Segment[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = arr[i].end;
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (min > arr[j].end) {
                    min = arr[j].end;
                    minIndex = j;
                }
            }
            Segment temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    
    private static class Segment {
        int start, end;
        
        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
