package DP.Tree_DP;

import java.util.List;

public class PointSet {

    List<Integer>[] g;
    int[][] dp;
    int n;
    int INF = 0x3f3f3f3f;

    /**
     * 最大独立点集
     * 求在树上不相邻的点集的最多有多少个
     *
     * dp[u][0] 表示不选择 u 节点 的最大数目
     * dp[u][1] 表示选择 u 节点 的最大数目
     *
     * if u is leaf:
     *      dp[u][0] = 0
     *      dp[u][1] = 1
     * else:
     *      dp[u][0] = sum{ max{ dp[v][0], dp[v][1] } v = son(u) } // u 不选的话，v 可选可不选
     *      dp[u][1] = sum{ dp[v][0] v = son(u) } + 1              // u 选的话，v 一定不能选
     */
    public int max_non_adjacent(int n) {
        this.n = n;
        dp = new int[n][2];
        dfs(0, -1);
        return Math.max(dp[0][0], dp[0][1]);
    }
    private void dfs(int u, int p) {
        dp[u][0] = 0;
        dp[u][1] = 1;
        for (int v : g[u]) {
            if (v == p) continue;
            dfs(v, u);
            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
            dp[u][1] += dp[v][0];
        }
    }

    /**
     * 最小点覆盖集
     * 求最小的点集可以覆盖数的所有边 （一条边只要有一个端点被选择了，就是被覆盖了）
     *
     * dp[u][0] 表示不选择 u 节点 的最小数目
     * dp[u][1] 表示选择 u 节点 的最小数目
     *
     * if u is leaf:
     *      dp[u][0] = 0
     *      dp[u][1] = 1
     * else:
     *      dp[u][0] = sum{ dp[v][1] v = son(u) }                       // u 不选，v 一定要选才能覆盖边
     *      dp[u][1] = sum{ min{ dp[v][0], dp[v][1] } v = son(u) } + 1  // u 选了，v 可选可不选
     */
    public int min_cover(int n) {
        this.n = n;
        dp = new int[n][2];
        dfs1(0, -1);
        return Math.min(dp[0][0], dp[0][1]);
    }
    private void dfs1(int u, int p) {
        dp[u][0] = 0;
        dp[u][1] = 1;
        for (int v : g[u]) {
            if (v == p) continue;
            dfs1(v, u);
            dp[u][0] += dp[v][1];
            dp[u][1] += Math.min(dp[v][0], dp[v][1]);
        }
    }

    /**
     * 最小点支配集
     * 求最小的点集可以支配所有点。
     * 最小支配集 指的是从V中取尽量少的点组成一个集合，使得对于V中剩余的点都与取出来的点有边相连。
     * 一个点 v 被支配可能是 被选择 也可能是 与一个支配点相连，所以会出现 未选择被支配 和 未选择未被支配两种情况
     *
     * dp[u][0] 表示选择了 u 的最小数目
     * dp[u][1] 表示未选择 u 被支配 的最小数目
     * dp[u][2] 表示未选择 u 未被支配 的最小数目
     *
     * if u is leaf:
     *      dp[u][0] = 1
     *      dp[u][1] = INF       // 叶子节点没有点可以去覆盖它
     *      dp[u][2] = 0
     * else:
     *      dp[u][0] = sum{ min{ dp[v][0], dp[v][1], dp[v][2] } v = son(u) } + 1                 // u 选了，v 随便
     *      dp[u][2] = sum{ dp[v][1] v = son(u) }                                                // u 未选未被支配，v 一定不能选且要被支配
     *      dp[u][1] = sum{ min{ dp[v][0], dp[v][1] } v = son(u) 且至少一次 dp[v][0] 取得最小值 }   // u 未选被支配， v 至少有一个是选择了的，即至少一个 dp[v][0]
     *
     *      怎么保证至少取到了一次 dp[v][0] 呢， 可以用一个 flag 记录， 如果取到了 flag = 1， 同时维护一个 diff 用来记录 dp[v][0] - dp[v][1] 的差量的最小值
     *      tmp = sum{ min{ dp[v][0], dp[v][1] } v = son(u) }
     *      if flag == 1:
     *          dp[u][1] = tmp;
     *      else:
     *          dp[u][1] = tmp + diff    // 尝试更换差量最小的 dp[v][0] 与 dp[v][1]
     */
    public int control(int n) {
        this.n = n;
        dp = new int[n][3];
        dfs2(0, -1);
        return Math.min(dp[0][0], dp[0][1]);
    }
    private void dfs2(int u, int p) {
        dp[u][0] = 1;
        dp[u][1] = 0;
        dp[u][2] = 0;
        int flag = 0;
        int diff = INF;
        for (int v : g[u]) {
            if (v == p) continue;
            dfs(v, u);
            dp[u][0] += Math.min(dp[v][0], Math.min(dp[v][1], dp[v][2]));

            if (dp[v][1] == INF) {
                dp[u][2] = INF;
            } else {
                dp[u][2] += dp[v][1];
            }

            dp[u][1] += Math.min(dp[v][0], dp[v][1]);
            if (dp[v][0] < dp[v][1]) {                       // set flag if we use a dp[v][0]
                flag = 1;
            }
            diff = Math.min(diff, dp[v][0] - dp[v][1]);      // record min diff
        }
        if (g[u].size() == 0) {
            dp[u][1] = INF;       // if u is leaf
        }
        if (flag == 0) {          // if we don't choose a dp[v][0]
            dp[u][1] += diff;
        }
    }
}
