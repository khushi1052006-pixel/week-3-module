import java.io.*;
import java.util.*;

public class TradeAnalytics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        if (n < 5) return;

        long[] arr = new long[n];
        long totalSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            totalSum += arr[i];
        }

        double overallAverage = (double) totalSum / n;

        quickSortDescending(arr, 0, n - 1);

        // 1. Display sorted trade values
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString().trim());

        // 2. Top 5 values
        System.out.print("Top 5: ");
        long top5Sum = 0;
        for (int i = 0; i < 5; i++) {
            top5Sum += arr[i];
            System.out.print(arr[i] + (i == 4 ? "" : " "));
        }
        System.out.println();

        // 3. Average of Top 5
        long top5Avg = top5Sum / 5; // Example expects an integer average
        System.out.println("Average of Top 5: " + top5Avg);

        // 4. Count values greater than overall average
        int countAboveAvg = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > overallAverage) {
                countAboveAvg++;
            } else {
                break; // Since it's sorted descending, no more values will be greater
            }
        }
        System.out.println("Values Above Overall Average: " + countAboveAvg);
    }

    private static void quickSortDescending(long[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDescending(arr, low, high);
            quickSortDescending(arr, low, pi - 1);
            quickSortDescending(arr, pi + 1, high);
        }
    }

    private static int partitionDescending(long[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        long pivot = arr[mid];

        long temp = arr[mid];
        arr[mid] = arr[high];
        arr[high] = temp;

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] > pivot) { // Greater than for descending order
                i++;
                long t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        long t = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = t;

        return i + 1;
    }
}