package DP.Knapsack;

public class KnapsackSetUnion {
    int N = 100;     // N set of items
    int M = 100;     // M items in a set
    int V = 100;     // max volume of an item
    int[] dp = new int[V + 1];
    int[][] v = new int[N][M];
    int[][] w = new int[N][M];

    /**
     * 是选择本组的某一件，还是一件都不选。
     * 在某一组内，对组内物品进行01背包。
     * 注意k层循环必须在j层循环里面才能保证每组只选一个
     */
    public int set_union_pack() {
        for (int i = 0; i < N; i++) {            // for N sets
            for (int j = V; j >= 0; j--) {       // update dp
                for (int k = 0; k < M; k++) {    // for M items
                    if (j >= v[i][k]) {
                        dp[j] = Math.max(dp[j], dp[j - v[i][k]] + w[i][k]);
                    }
                }
            }
        }
        return dp[V];
    }
}
