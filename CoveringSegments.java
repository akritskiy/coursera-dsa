import java.util.*;

public class CoveringSegments {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Input number of segments
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
        
        //Print
        System.out.println(m);
        for (int i=0; i<m; i++) {
            System.out.print(result[i] + " ");
        }
    }

    //Optimal points method
    private static int[] optimalPoints(Segment[] segments) {
        int n = segments.length; //Initialize n.
        //n will be the scope of the method. When it is determined that a point covers a segment, that segment will be moved outside the scope
        
        sortSegments(segments, n); //To begin, sort the array

        int[] points = new int[n]; //Declare points array, which will store our points, and initialize numPoints
        int numPoints = 0;

        while (n > 0) {
            int point = segments[0].end; //Select the end point of the left-most segment (if viewed on a number line, 0...1...2...etc.)
            points[numPoints] = point;
            numPoints++;

            for (int j=0; j<n; j++) { //Check each segment
                if (point >= segments[j].start && point <= segments[j].end) {
                    //If the point lies on the segment, the segment is moved outside the scope, and the segment at index n-1 takes its place
                    Segment temp = segments[n-1];
                    segments[n-1] = segments[j];
                    segments[j] = temp;
                    n--; //Shorten the scope
                    j--; //Decrement j to check the new segment at index j
                }
            }
            sortSegments(segments, n); //Re-sort the segments array, but only within the scope
        }

        //Clean up the points array
        int[] result = new int[numPoints];
        for (int i=0; i<numPoints; i++) {
            result[i] = points[i];
        }
        return result;
    }

    //Sort the segments array by end point (least to greatest), index range [0, n-1]
    private static void sortSegments(Segment[] arr, int n) {
        for (int i=0; i<n-1; i++) {
            int min = arr[i].end;
            int minIndex = i;
            for (int j=i+1; j<n; j++) {
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
