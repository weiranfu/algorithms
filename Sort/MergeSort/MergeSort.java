package Sort.MergeSort;

/**
 * 1. Mergesort the left half
 * 2. Mergesort the right half
 * 3. Merge the results
 *
 * Time complexity: O(n*lgn)    Space complexity: O(n)
 */
public class MergeSort {

    int[] tmp;

    // Sort array destructively.
    public MergeSort(int[] x) {
        int n = x.length;
        tmp = new int[n];
        mergeSort(x, 0, n - 1);
    }

    private void mergeSort(int[] A, int l, int r) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        mergeSort(A, l, mid);
        mergeSort(A, mid + 1, r);

        int k = 0, i = l, j = mid + 1;                          // merge two arrays
        while (i <= mid && j <= r) {
            if (A[i] <= A[j]) tmp[k++] = A[i++];
            else tmp[k++] = A[j++];
        }
        while (i <= mid) tmp[k++] = A[i++];
        while (j <= r) tmp[k++] = A[j++];

        for (i = l, j = 0; i <= r; i++, j++) A[i] = tmp[j];     // write back from tmp
    }
}
