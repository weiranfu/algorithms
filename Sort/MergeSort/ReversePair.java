package Sort.MergeSort;

/**
 * 求数组中逆序对的个数
 * 逆序对的定义如下：对于数列的第 i 个和第 j 个元素，如果满足 i < j 且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * [5,2,2,1,4]   (5,2), (2,1) 都是逆序对
 *
 * 1. Divide array into two parts [l, mid] [mid+1, r]
 * 2. Count the number of pairs in left part and right part
 * 3. Count the number of pairs with one member in left part other member in right part.
 * 4. return the total count
 *
 * How to count 3?
 * Recall the steps merging two sorted array. If we find A[i] > B[j], then A[i],A[i+1],...A[M] all of them are > B[j]
 * Then we find (M-i+1) pairs for B[j]
 */
public class ReversePair {

    int[] A, tmp;

    public long cntReversePair(int[] A) {
        this.A = A;
        int n = A.length;
        tmp = new int[n];
        return mergeSort(0, n - 1);
    }

    private long mergeSort(int l, int r) {
        if (l >= r) return 0;
        int mid = l + (r - l) / 2;

        long cnt = mergeSort(l, mid) + mergeSort(mid + 1, r);   // count left & right part

        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (A[i] <= A[j]) tmp[k++] = A[i++];
            else {
                tmp[k++] = A[j++];
                cnt += mid - i + 1;                             // count cross pairs
            }
        }
        while (i <= mid) tmp[k++] = A[i++];
        while (j <= r) tmp[k++] = A[j++];
        for (i = l, j = 0; i <= r; i++, j++) A[i] = tmp[j];
        return cnt;
    }
}
