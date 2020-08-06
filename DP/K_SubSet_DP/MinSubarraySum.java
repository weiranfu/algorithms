package DP.K_SubSet_DP;

/**
 * Minimize the largest sum of K subarrays.
 *
 * dp[k][i] means we divide a[:i] into k parts
 *
 * dp[k][i] = min{ max{ dp[k-1][j] + sum(j+1,i) for j in [1, i-1] } }
 *
 * Initialize:
 *      if k == 1: dp[1][i] = a[i]
 *      if k > i: dp[k][i] = Integer.MAX_VALUE  (我们不能将 i 分成 k 份 如果 i 小于 k)
 *
 * 求最大值的最小值问题还可以用 binary search 来做
 * 如果确定一个sum-limit, 我们可以用贪心去组成 subarrays
 */
public class MinSubarraySum {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[1][i] = preSum[i];                       // initialize
        }
        for (int k = 2; k <= m; k++) {
            for (int i = k; i <= n; i++) {              // i must >= k
                int min = Integer.MAX_VALUE;
                for (int j = 1; j < i; j++) {
                    min = Math.min(min, Math.max(dp[k - 1][j], preSum[i] - preSum[j]));
                }
                dp[k][i] = min;
            }
        }

        return dp[m][n];
    }


    class Solution {
        public int splitArray(int[] nums, int m) {
            int n = nums.length;
            long left = 0, right = 0;
            for (int i : nums) {
                left = Math.max(left, i);// left bound is max of array
                right += i;              // right bound is sum of array
            }
            while (left < right) {
                long mid = left + (right - left) / 2;
                if (check(nums, m, mid)) {   // find smaller sum value
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return (int)left;
        }

        // small --- large
        // n          1
        // if (check) we will search for smaller max
        private boolean check(int[] nums, int m, long max) {
            int n = nums.length;
            int count = 0;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (sum + nums[i] > max) { // greedy
                    count++;
                    sum = 0;
                }
                sum += nums[i];
            }
            count++;                  // last subarray
            return count <= m;
        }
    }
}
