package Sort.InsertionSort;

public class InPlaceInsertionSort {

    public static void sort(int[] x) {
        insertionSort(x);
    }

    public static void insertionSort(int[] x) {
        for (int i = 0; i < x.length; ++i) {
            int insertValue = x[i];
            int j = i - 1;
            while (j >= 0 && x[j] > insertValue) {
                x[j + 1] = x[j];
                j--;
            }
            x[j + 1] = insertValue;  // Insert value into right place.
        }
    }
}
