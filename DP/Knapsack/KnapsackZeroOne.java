package DP.Knapsack;

import java.util.Arrays;

public class KnapsackZeroOne {
    int V = 5000;
    int N = 5000;
    int[] dp = new int[V + 1];
    int[] v = new int[N];           // volume
    int[] w = new int[N];           // weight

    /**
     * 如果背包不一定放满
     */
    public int zero_one_pack1() {

        Arrays.fill(dp, 0);                     // we don't need to use up all volume in dp[i]

        for (int i = 0; i < N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[i] = Math.max(dp[i], dp[i - v[i]] + w[i]);
            }
        }
        return dp[V];
    }

    /**
     * 如果背包一定得放满
     */
    public int zero_one_pack() {

        Arrays.fill(dp, Integer.MIN_VALUE);         // if we want to use up all available volume.
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[i] = Math.max(dp[i], dp[i - v[i]] + w[i]);
            }
        }
        int res = 0;
        for (int i = 0; i <= V; i++) {
            res = Math.max(res, dp[i]);             // max value is not dp[V]
        }
        return res;
    }

    /**
     * 二维做法
     */
    public int zero_one_2D() {
        int[][] f = new int[N + 1][V + 1];

        f[0][0] = 0;                              // initialize

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                if (j >= i) f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - v[i]] + w[i]);
                else f[i][j] = f[i - 1][j];
            }
        }
        return f[N][V];
    }
}
