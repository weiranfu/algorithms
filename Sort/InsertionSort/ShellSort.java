package Sort.InsertionSort;

public class ShellSort {

    public static void sort(String[] x) {
        shellSort(x);
    }

    /** Use interval d to divide array into d groups. Perform insertion sort on each groups.
     * Every time sort d groups, decrement d by 2.
     * @param s
     */
    private static void shellSort(String[] s) {
        int d = s.length / 2;
        while (d >= 1) {
            for (int x = 0; x < d; x++) {      // For d groups, perform insertion sort on each of them.
                for (int i = x + d; i < s.length; i = i + d) {
                    String insertValue = s[i];
                    int j;
                    for (j = i - d; j >= 0 && s[j].compareTo(insertValue) > 0; j = j - d) {
                        s[j + d] = s[j];
                    }
                    s[j + d] = insertValue;
                }
            }
            d = d / 2;
        }
    }
}
