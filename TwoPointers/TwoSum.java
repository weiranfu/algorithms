package TwoPointers;

/**
 * 给定两个 升序排序 的有序数组A和B，以及一个目标值x。数组下标从0开始。
 * 请你求出满足A[i] + B[j] = x的数对(i, j)。
 *
 * 单调性：当 i 增加时，j 只能减少才可能有 A[i] + B[j] == x
 */
public class TwoSum {

    public int[] twoSum(int[] A, int[] B, int x) {
        int m = A.length, n = B.length;

        int i, j;
        for (i = 0, j = n - 1; i < m; i++) {

            while (j >= 0 && A[i] + B[j] > x) j--;

            if (A[i] + B[j] == x) return new int[]{i, j};
        }
        return new int[0];
    }
}
