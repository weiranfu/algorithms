package DP.TSP;

/**
 * Traveling Salesman Problem(TSP) (Hamiltonian Cycle)
 * 给定一系列城市和每对城市之间的距离，求解访问每一座城市一次并回到起始城市的最短回路。
 * 城市 0 ~ n-1
 * 我们可以枚举 1 ~ n-1 的排列(n!种)，可得一条路径 0 - 1 - 2 ... - n - 1 - 0
 * 当我们搜索下一个位置的时候，并不关心前面具体的排列是什么样，我们只关系前面排列用过了哪些元素，
 * 即可以把排列抽象为集合的时候，可以用状态压缩来表示集合。(且状态压缩必须保证元素没有重复，可以用集合表示)
 *
 * dp[S][i] 表示 集合 S 里面的点都是我已经去过的，i 是我最后到达的点，
 *
 * dp[S][i] = dp[K][j] + dis[j][i]    K 不包含 i，但包括 j，K | i = S
 * 注意城市下标需从 0 开始，方便状态转移
 */
public class TSP {

    int n;
    int N = 16;
    int M = 1 << 16;
    int[][] dis = new int[N][N];
    int[][] dp = new int[M][N];
    int INF = 0x3f3f3f3f;

    public int tsp(int n) {
        this.n = n;

        for (int s = 0; s < 1 << n; s++) {
            for (int i = 0; i < n; i++) {
                dp[s][i] = INF;
            }
        }
        dp[1][0] = 0;                        // 初始化

        for (int s = 0; s < 1 << n; s++) {  // 从状态 0 遍历所有状态到最后
            for (int i = 0; i < n; i++) {   // 从 j 转移到 i
                if ((s >> i & 1) == 1) {    // 包含 i
                    int state_j = s & ~(1 << i); // 去除 i
                    for (int j = 0; j < n; j++) {
                        if ((state_j >> j & 1) == 1) {  // 包含 j
                            if (s == (1 << n) - 1) {       // 如果所有点都遍历了，返回原点
                                dp[s][i] = Math.min(dp[s][i], dp[state_j][j] + dis[j][i] + dis[i][0]);
                            } else {
                                dp[s][i] = Math.min(dp[s][i], dp[state_j][j] + dis[j][i]);
                            }
                        }
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            res = Math.min(res, dp[M][i]);
        }
        return res;
    }
}

