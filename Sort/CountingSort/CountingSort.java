package Sort.CountingSort;

public class CountingSort {

    public static void sort(int[] x) {
        countingSort(x);
    }

    public static void countingSort(int[] x) {
        int max = x[0];
        int min = x[0];
        for (int i : x) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int[] count = new int[max - min + 1];
        for (int i : x) {
            count[i - min]++;
        }
        // Count will store the total nums of items before item i.
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            sum += count[i];
            count[i] = sum;
        }
        // Scan the original array backwards to find right position of item.
        int[] res = new int[x.length];
        for (int i = x.length - 1; i >= 0; i--) {
            int pos = count[x[i] - min] - 1;
            res[pos] = x[i];
            count[x[i] - min]--;
        }
        for (int i = 0; i < x.length; i++) {
            x[i] = res[i];
        }
    }
}
