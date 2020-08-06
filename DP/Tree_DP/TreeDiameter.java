package DP.Tree_DP;

import java.util.ArrayList;
import java.util.List;

/**
 * The diameter of tree is the max length between two nodes in a tree.
 */
public class TreeDiameter {

    /**
     *  贪心
     *  两次DFS寻找任一一个最长链  O(n)
     *  从任一点出发找到最远点v，再从v出发找到最远点u，最长链即为v-u
     */
    List<Integer>[] g;
    int[] edgeTo;
    int node;
    int max;
    public int treeDiameter(int[][] edges) {
        int n = edges.length;
        g = new List[n + 1];
        edgeTo = new int[n];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        max = -1;                   // initialize
        dfs(0, -1, 0);
        max = -1;
        dfs(node, -1, 0);
        return max;
    }
    private void dfs(int v, int p, int len) {
        if (len > max) {
            max = len;
            node = v;
        }
        edgeTo[v] = p;
        for (int u : g[v]) {
            if (u == p) continue;
            dfs(u, v, len + 1);
        }
    }


    /**
     * 树状DP  O(n)
     * 通过当前节点的最长路径 是 最长的两条从当前节点到叶子的路径之和
     * 当前节点将 最大的路径长+1 返回给 父节点
     *
     * dp[v][0] 是从某一点出发DFS 经过 v 的最长链
     * dp[v][1] 是从某一点出发DFS 经过 v 的次长链
     *
     * 如果 v 是叶子:
     *      dp[v][0] = 0
     *      dp[v][1] = -INF
     * else:
     *      dp[v][0] = max{ dp[u][0] u = son(v) }
     *      dp[v][1] = second_max{ dp[u][0] u = son(v) }
     */
    int[][] dp;
    public int treeDP(int[][] edges) {
        int n = edges.length;
        g = new List[n + 1];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        dp = new int[n + 1][2];
        dfs(0, -1);
        return max;
    }

    private void dfs(int v, int p) {
        for (int u : g[v]) {
            if (u == p) continue;                   // pass parent

            dfs(u, v);                              // dfs children

            if (dp[v][0] < dp[u][0] + 1) {          // update max
                dp[v][1] = dp[v][0];
                dp[v][0] = dp[u][0] + 1;
            } else if (dp[v][1] < dp[u][0] + 1) {   //update second max
                dp[v][1] = dp[u][0] + 1;
            }
        }
        max = Math.max(max, dp[v][0] + dp[v][1]);   //record max length
    }
}
