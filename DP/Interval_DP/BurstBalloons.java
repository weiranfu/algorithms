package DP.Interval_DP;

/**
 * 给出1-n 个气球，我们选择一种戳破气球的顺序，每次戳破一个气球，我们可以获得 nums[left]*nums[i]*nums[right] 积分，
 * 其中left 和 right 是 i 气球 左侧和右侧的气球（因为有些气球已经被戳破，所以他们可能不相邻），求最大可能获得的积分
 *
 * dp[i][j] 表示开区间 [i,j] (i 和 j 中间的气球已经都被戳破了，所以left 和 right 就是边界 i， j）
 *
 * 先戳破区间 [i][k] 和 [k][j]，最后戳破 k 气球
 * dp[i][j] = max{ dp[i][k] + dp[k][j] +  nums[i] * nums[k] * nums[j] for k in [i+1, j-1]}
 */
public class BurstBalloons {

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] newNums = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            newNums[i] = nums[i - 1];
        }
        newNums[0] = 1;
        newNums[n + 1] = 1;                      // 给所以气球两边补上两个 1 的气球

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 3; len <= n + 2; len++) {
            for (int i = 0, j = len - 1; j < n + 2; i++, j++) {
                int max = Integer.MIN_VALUE;
                for (int k = i + 1; k < j; k++) {
                    max = Math.max(max, dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][n + 1];
    }
}
