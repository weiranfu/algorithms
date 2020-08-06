package DP.Knapsack;

import java.util.ArrayList;
import java.util.List;

/*
    01 背包，输出一个字典序最小的最优解的选法
    f[i][m] == f[i - 1][m]          不选第i个物品
    f[i][m] == f[i - 1][m - v] + w  选第i个物品
    因为最后输出要从前往后检查物品，所以构建dp时，从后往前加入物品
 */
public class KnapsackPrint {
    int N = 5000, V = 5000;
    int[][] dp = new int[N + 1][V + 1];
    int[] v = new int[N];
    int[] w = new int[N];

    public List<Integer> print() {
        List<Integer> res = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= V; j++) {
                if (j >= v[i]) dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j - v[i]] + w[i]);
                else dp[i][j] = dp[i + 1][j];
            }
        }
        int vol = V;
        for (int i = 0; i < N; i++) {
            if (vol - v[i] < 0) continue;
            if (dp[i][vol] == dp[i + 1][vol - v[i]] + w[i]) {  // 我们选了该物品
                res.add(i);
                vol -= v[i];
            }
        }
        return res;
    }
}
