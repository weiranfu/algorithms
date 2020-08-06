package DP.LCS_LIS;

import java.util.*;

/**
 * Longest Increasing Subsequences
 */
public class LIS {

    /**
     * DP   O(n^2)
     * dp[i] 选了第i个元素的最长上升子序列 （必须选第i个元素)
     * dp[i] = max{ dp[j] j < i && s[j] < s[i] } + 1
     */
    public int lis_dp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = 1;             // 一个元素长度为1

        int res = 1;
        for (int i = 1; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = (max == Integer.MIN_VALUE) ? 1 : max + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * tails[i] 数组 表示长度为i的LIS的最小结尾元素  O(nlogn)
     * 1 5 2 3 6 4
     * tails 长度  1 2 3 4 5
     *  add 1     1
     *  add 5     1 5
     *  add 2     1 2
     *  add 3     1 2 3
     *  add 6     1 2 3 6
     *  add 4     1 2 3 4
     */
    public int lis_tails(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int pos = Arrays.binarySearch(tails, 0, len, nums[i]);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            tails[pos] = nums[i];
            if (pos == len) {
                len++;
            }
        }
        return len;
    }

    /**
     * BIT  O(nlogn)
     * 维护一个权值数组，以dp[i] 为值，nums[i] 为坐标
     * 当我们要求dp[i]时，我们要找 nums[j] < nums[i] 中 dp[j] 最大的
     * 这就转化成了区间最值问题 + 单点更新
     * 在权值数组中，如果有重复的nums[i], 可以直接更新dp[i]. 因为后面更新的 nums[i] 的 LIS 一定是更长的
     * 因为区间最值从最左侧开始，可以用BIT，否则应用Segment Tree
     * 因为单点更新是越来越大，所以在更新max时可以用BIT
     */
    public class Solution {
        int N;
        int[] bit;
        List<Integer> list;

        public int lis_bit(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int n = nums.length;
            list = new ArrayList<>();
            for (int a : nums) {
                list.add(a);
            }
            list = new ArrayList<>(new HashSet<>(list));
            Collections.sort(list);
            N = list.size();

            bit = new int[N + 1];
            int res = 1;
            for (int a : nums) {
                int idx = find(a);
                int max = getmax(idx - 1);
                add(idx, max + 1);
                res = Math.max(res, max + 1);
            }
            return res;
        }
        private void add(int x, int v) {
            for (int i = x; i <= N; i += lowbit(i)) {
                bit[i] = Math.max(bit[i], v);
            }
        }
        private int getmax(int x) {
            int res = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                res = Math.max(res, bit[i]);
            }
            return res;
        }
        private int find(int x) {
            return Collections.binarySearch(list, x) + 1;     // BIT start from 1
        }
        private int lowbit(int x) {return x & (-x);}
    }

}


