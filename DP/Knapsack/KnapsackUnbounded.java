package DP.Knapsack;

public class KnapsackUnbounded {
    int V = 5000;
    int N = 5000;
    int[] dp = new int[V + 1];
    int[][] dp2 = new int[N + 1][V + 1];
    int[] v = new int[N];           // volume
    int[] w = new int[N];           // weight

    public int unbounded_pack() {
        for (int i = 0; i < N; i++) {
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        return dp[V];
    }

    /**
     * dp[i][j] = max{ dp[i-1][j], dp[i-1][j-kv] + kw for k in [1,V/v] }
     *          = max{ dp[i-1][j], dp[i-1][j-v] + w, dp[i-1][j-2v] + 2w, ... dp[i-1][j-kv] + kw}
     * dp[i][j-v] + w =       max{ dp[i-1][j-v] + w, dp[i-1][j-2v] + 2w, ... dp[i-1][j-kv] + kw}
     * 即可化简为
     * dp[i][j] = max{ dp[i-1][j], dp[i][j-v] + w }
     */
    public int unbounded_2D() {
        dp2[0][0] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= V; j++) {
                if (j >= v[i]) dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i][j - v[i]]);
                else dp2[i][j] = dp2[i - 1][j];
            }
        }
        return dp2[N][V];
    }
}
