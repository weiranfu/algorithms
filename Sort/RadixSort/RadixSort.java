package Sort.RadixSort;

import java.util.Arrays;

public class RadixSort {

    public static void sort(String[] s) {
        radixSort(s);
    }

    private static void radixSort(String[] s) {
        String[] sortedArray = new String[s.length];
        for (int k = s.length - 1; k >= 0; k--) {
            // Perform Counting Sort
            int[] count = new int[128];
            for (String str : s) {
                int index = getCharAt(str, k);
                count[index]++;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            for (int i = s.length - 1; i >= 0; i--) {
                int index = getCharAt(s[i], k);
                int sortedIndex = count[index] - 1;
                sortedArray[sortedIndex] = s[i];
                count[index]--;
            }
            // Pass sortedArray to s, the next counting sort is based on previous one.
            int p = 0;
            for (String str : sortedArray) {
                s[p++] = str;
            }
        }
    }


    // Fill the tail of string with 'a' if string length is small.
    private static int getCharAt(String str, int k) {
        if (k >= str.length()) {
            return 0;
        }
        return str.charAt(k);
    }
}
