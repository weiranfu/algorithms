package Sort.SelectionSort;


/**
 * * Find the smallest item
 * * Move it to the front
 * * Selection sort the remaining N-1 items (without touching the front item)
 *
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 */
public class SelectionSort {
    public static void sort(int[] x) {
        selectionSort(x);
    }

    /** Sorts strings destructively starting from item start. */
    private static void selectionSort(int[] x) {
        int n = x.length;
        for (int i = 0; i < n; i++) {
            int idx = i;
            for (int j = i; j < n; j++) {
                if (x[i] < x[idx]) {
                    idx = i;
                }
            }
            swap(x, i, idx);
        }
    }

    public static void swap(int[] x, int a, int b) {
        int temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
