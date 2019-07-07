public class SelectionSort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) { 
        for (int i = 0; i < x.length; i += 1) {
            // find the smallest item
            for (int j = i + 1; j < x.length; j += 1) {
            	if (x[j] < x[i]) {
            		int tmp = x[i];
            		x[i] = x[j];
            		x[j] = tmp;
            	}
            }
            // move it to the front
            // selection sort the rest (using recursion?)
        }
    }
}
