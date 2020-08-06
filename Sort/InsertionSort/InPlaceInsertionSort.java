package Sort.InsertionSort;

public class InPlaceInsertionSort {

    public static void sort(String[] x) {
        insertionSort(x);
    }

    public static void insertionSort(String[] x) {
        for (int i = 0; i < x.length; ++i) {
            String insertValue = x[i];
            int j = i - 1;
            while (j >= 0 && x[j].compareTo(insertValue) > 0) {
                x[j + 1] = x[j];
                j--;
            }
            x[j + 1] = insertValue;  // Insert value into right place.
        }
    }
}
