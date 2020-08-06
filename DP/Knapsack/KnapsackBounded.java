package DP.Knapsack;

public class KnapsackBounded {
    int V = 5000;
    int N = 5000;
    int[] dp = new int[V + 1];
    int[] v = new int[N];           // volume
    int[] w = new int[N];           // weight
    int[] c = new int[N];

    /**
     * 将第i个物品有c个看成c个相同物品进行01背包(每个物品只能用一次)
     */
    public int bounded_pack1() {
        for (int i = 0; i < N; i++) {
            for (int k = 1; k <= c[i]; k++) {            // c[i] 个相同物品
                for (int j = V; j >= v[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }
        }
        return dp[V];
    }


    /**
     * 二进制 优化
     * 如10件物品A, 则插入十条记录到v和w数组中，实际上这一过程可以进行优化!
     * 我们可以把十件物品A分成若干份，这若干份必须可以组合成0～10以内的任何一个数字。
     * 做法是：1,2,4,...,2^(k-1),10-2^(k-1)
     * 即：10可以分为 1，2，4，3
     * 显然这四个数字，可以组合成0～10以内的任何一个数字，如 8 = 1 + 4 + 3
     * 每一份对应的体积和价值，用系数乘以1件物品的体积和价值。
     * 这么做的好处，可以把时间复杂度从O(N*V*C)降为O(N*V*logC)，剩下的继续用01背包问题的解法求解。
     */
    public int bounded_pack2() {
        for (int i = 0; i < N; i++) {
            int cnt = c[i];
            for (int k = 1; k <= cnt; k *= 2) {   // 二进制分解
                cnt -= k;
                for (int j = V; j >= k * v[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + w[i]);
                }
            }
            for (int j = V; j >= cnt * v[i]; j--) {                 // 对剩余的cnt做一次01背包
                dp[j] = Math.max(dp[j], dp[j - cnt * v[i]] + w[i]);
            }
        }
        return dp[V];
    }
}
