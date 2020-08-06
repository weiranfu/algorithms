package Sort.QuickSort;

import java.util.Random;

public class FindKthSmallest {

    Random random;
    int k, n;

    public int findKthLargest(int[] nums, int k) {
        random = new Random();
        this.k = k;
        n = nums.length;
        return quickSort(nums, 0, n - 1, k);
    }

    private int quickSort(int[] A, int l, int r, int k) {
        if (l == r) return A[l];

        int rand = l + random.nextInt(r - l + 1);  // randomly choose an element to swap with last element
        swap(A, r, rand);
        int pivot = A[r];
        int i = l;
        for (int j = l; j <= r - 1; j++) {
            if (A[j] <= pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, r);

        // [l, i-1][i][i+1, r]
        if (i == k) return A[i];
        else if (i < k) return quickSort(A, i + 1, r, k);
        else return quickSort(A, l, i - 1, k);
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
