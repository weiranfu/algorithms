package Math.Difference;

/**
 * 差分数组数组是前缀和数组的逆运算
 *
 * 原数组： A[0],A[1],...,A[n]
 * 差分数组 B[0],B[1],...,B[n]
 *
 * 满足 A[i] = B[0] + B[1] +...+ B[i]  即 A 是 B 的前缀和
 * 则 B[i] = A[i] - A[i-1]
 *
 * 应用：给 A 数组区间 [l, r] 中每个数都加 c  可以达到 O(1) 复杂度
 * 因为 A 是 B 的前缀和
 * 只需要 B[l] += c, B[r+1] -= c 即可
 *
 *
 */
public class Difference {

    int n;
    int[] A, B;

    public int[] getDifference(int[] nums, int[][] operations) {
        n = nums.length;
        A = new int[n + 2];
        B = new int[n + 2];                      // 1 based array and 我们可能对 B[r+1] -= c 进行操作
        for (int i = 1; i<= n; i++) {
            A[i] = nums[i - 1];
            add(i, i, A[i]);                     // 想象成 A 原来是空的，然后在区间 [i,i] 插入 A[i] 从而得到初始 B
        }

        for (int[] op : operations) {
            int l = op[0], r = op[1], c = op[2];
            add(l, r, c);
        }

        for (int i = 1; i <= n; i++) {             // 最后对 B 求前缀和 得到修改后的 A
            B[i] += B[i - 1];
        }
        return B;
    }

    private void add(int l , int r, int c) {
        B[l] += c;
        B[r + 1] -= c;
    }
}
