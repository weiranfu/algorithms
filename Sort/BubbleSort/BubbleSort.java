package Sort.BubbleSort;

public class BubbleSort {

    public static void sort(int[] x) {
        bubbleSort(x);
    }

    public static void bubbleSort(int[] x) {
        int n = x.length;
        for (int i = 0; i < n - 1; i++) {           // at most n-1 times
            for (int j = 0; j < n - 1 - i; j++) {   // sorted area [n - i, n - 1];
                if (x[j] > x[j + 1]) {
                    swap(x, j, j + 1);
                }
            }
        }
    }


    public static void bubbleSort2(int[] x) {
        int n = x.length;
        int bound = n - 1;
        int last = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean isSwap = false;
            for (int j = 0; j < bound; j++) { // Sorted area is [sortBorder + 1, n-1]
                if (x[j] > x[j + 1]) {
                    swap(x, j, j + 1);
                    isSwap = true;
                    last = j;
                }
            }
            bound = last;
            if (!isSwap) {
                break;
            }
        }
    }

    private static void swap(int[] x, int a, int b) {
        int tmp = x[a];
        x[a] = x[b];
        x[b] = tmp;
    }
}
