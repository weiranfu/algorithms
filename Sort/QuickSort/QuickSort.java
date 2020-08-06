package Sort.QuickSort;

import java.util.Random;

/**
 * 1. pick a pivot (The last element)
 *    Before we pick the pivot, we need to randomized choose an element to swap with last element.
 * 2. partition the array into two part, one part is <= pivot, other part is > pivot
 * 3. Quick sort left part and right part.
 *
 * Time Complexity: best case: O(nlogn), worst case: O(n^2), on average: O(nLogn)
 * Space Complexity: O(logn) (call stack)
 *
 * The worst case is that all elements are same or array is sorted already.
 */
public class QuickSort {

    Random random;

    public QuickSort(int[] A) {
        int n = A.length;
        random = new Random();
        quickSort(A, 0, n - 1);
    }

    private void quickSort(int[] A, int l, int r) {
        if (l >= r) return;

        int rand = l + random.nextInt(r - l + 1);       // randomly choose an element to swap with last element
        swap(A, r, rand);
        int pivot = A[r];
        int i = l;
        for (int j = l; j <= r - 1; j++) {
            if (A[j] <= pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, r);                              // swap pivot to position i, A[i] is the Kth smallest element (K == i - l + 1)

        quickSort(A, l, i - 1);
        quickSort(A, i + 1, r);
    }

    void swap(int[] A, int a, int b) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
