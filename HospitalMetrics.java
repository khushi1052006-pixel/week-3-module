import java.io.*;
import java.util.*;

public class HospitalMetrics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        if (n == 0) return;

        int[] arr = new int[n];
        double sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        heapSort(arr);

        // 1. Display sorted response times
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString().trim());

        // 2 & 3. Fastest and Slowest
        int fastest = arr[0];
        int slowest = arr[n - 1];
        System.out.println("Fastest: " + fastest);
        System.out.println("Slowest: " + slowest);

        // 4. Average response time
        double average = sum / n;
        System.out.printf(Locale.US, "Average: %.2f\n", average);

        // 5. Cases handled faster (less time) than average
        int fasterCasesCount = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < average) {
                fasterCasesCount++;
            } else {
                break; // Because the array is sorted ascending
            }
        }
        System.out.println("Cases Faster Than Average: " + fasterCasesCount);

        // 6. Percentage of cases handled faster than average
        double percentage = ((double) fasterCasesCount / n) * 100;
        System.out.printf(Locale.US, "Percentage: %.2f%%\n", percentage);
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}