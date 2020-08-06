package DP.Interval_DP;

/**
 * 石子归并问题
 * 给出一堆石子，每次选择相邻的两堆合在一起，付出代价是两者重量只和，求最小代价合并所有石子
 *
 * dp[l][r] 表示区间 [l, r]， 考虑在 [l, r] 中切一刀合并，枚举所有可能位置
 *
 * dp[l][r] = min{ dp[l][i] + dp[i + 1][r] + cost[l, r] for i in [l, r-1] }
 *
 * 复杂度是 O(n^3)
 */
public class CombineStones {
    int maxn = 305;
    int n;
    int[] stones = new int[maxn];
    int[] preSum = new int[maxn];      // 前缀和求 cost[l, r]
    int[][] dp = new int[maxn][maxn];

    public int mergeStones(int n) {
        this.n = n;

        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {                  // k in [i, j-1] to divide it
                    min = Math.min(min, dp[i][k] + dp[k + 1][j] + preSum[j + 1] - preSum[i]);
                }
                dp[i][j] = min;
            }
        }

        return dp[0][n - 1];
    }

    /**
     * 如果石头是一个圆排列，我们可以复制一份一样的石头列，接在原来的石头后面来模拟圆排列
     * [1,2,3,4]
     * => [1,2,3,4,1,2,3,4]
     * 然后我们再到长度为 n 的区间里面去找最小cost
     */
    public int mergeRingStones(int n) {
        int[] newStones = new int[2 * maxn];
        int[] preSum2 = new int[2 * maxn];
        int[][] dp2 = new int[2 * maxn][2 * maxn];

        for (int i = 1; i <= 2 * n; i++) {
            preSum2[i] = preSum2[i - 1] + newStones[i - 1];
        }

        int res = Integer.MAX_VALUE;
        for (int s = 0; s < n; s++) {    // 尝试不同起点
            for (int len = 2; len <= n; len++) {
                for (int i = s, j = s + len - 1; j < s + n; i++, j++) {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j] + preSum2[j + 1] - preSum2[i]);
                    }
                    dp[i][j] = min;
                }
            }
            res = Math.min(res, dp[s][s + n - 1]);
        }

        return res;
    }
}
