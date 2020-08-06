package DP.Knapsack;

import java.util.Arrays;

/**
    求解背包问题的最优方案数
    f[j] 表示背包容积”恰好”为j时的最大价值和 ———— 最优解dp
    cnt[j] 表示背包容积”恰好”为j时取最优解的方案数 ———— 方案数dp
    最后找到最优解的数值，在g[j]里面只要与这个数相等的都是最优方案数
    因为最终不一定占满全部的背包体积，所以最优解不一定是f[m]
    01背包在这个地方就不一样了，因为01背包就算占不满m的体积到最后也可以转移到f[m]中，f[m]保留的就是最优解
    但是方案数中g[j]严格与f[i]相对应，必须找出准确的取最优解时的体积
    注意定义的这个“恰好”
 */
public class KnapsackCount {
    int N = 5000;
    int V = 5000;
    int[] dp = new int[V + 1];
    int[] cnt = new int[V + 1];
    int[] v = new int[N];
    int[] w = new int[N];

    public int countBest() {
        Arrays.fill(dp, Integer.MIN_VALUE);         // initial to MIN_VALUE
        dp[0] = 0;
        cnt[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = V; j >= v[i]; j--) {
                int tmp = Math.max(dp[j], dp[j - v[i]] + w[i]);
                int c = 0;
                if (tmp == dp[j]) c += cnt[j];
                if (tmp == dp[j - v[i]] + w[i]) c += cnt[j - v[i]];
                dp[j] = tmp;
                cnt[j] = c;
            }
        }

        int max = 0;
        for (int i = 0; i <= V; i++) {
            max = Math.max(max, dp[i]);
        }
        int res = 0;
        for (int i = 0; i <= V; i++) {
            if (dp[i] == max) {
                res += cnt[i];
            }
        }
        return res;
    }

    /**
     * 求方案总数
     * dp[i] = sum(dp[i], dp[i-v])
     */
    public int countTotal() {
        Arrays.fill(dp, 0);

        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = dp[j] + dp[j - v[i]];
            }
        }
        return dp[V];
    }
}
