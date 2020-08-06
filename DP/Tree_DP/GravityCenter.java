package DP.Tree_DP;

import java.util.Arrays;
import java.util.List;

/**
 * 树的重心：对于树上的每一个点，计算其所有子树中最大的子树节点数，这个值最小的点就是这棵树的重心。
 * 以树的重心为根时，所有子树的大小都不超过整棵树大小的一半。
 * 树中所有点到某个点的距离和中，到重心的距离和是最小的；如果有两个重心，那么到它们的距离和一样。
 * 类似于最小的曼哈顿距离在中位数取得
 *
 * size[u] 表示 u 节点儿子节点方向所有子树的节点数之和 + 1 （包括自己）
 *
 * size[u] = sum{ size[v] v = son(u) }
 *
 * n - size[u] 是 u 向父节点方向的子树的节点总数
 *
 * 求 u 的所有子树的最大节点数：
 *
 * max = { max{size[v] v = son(u)},  n - size[u] }   n 是节点总数
 */
public class GravityCenter {
    List<Integer>[] g;
    int n;
    int[] dp;       // size of tree
    int[] weight;   // max weight of neighbors
    int centroid;

    public int centroid(int n) {
        this.n = n;
        dp = new int[n];
        weight = new int[n];

        Arrays.fill(dp, 1);     // 所有子树 size = 1
        centroid = -1;
        dfs(0, -1);
        return centroid;
    }

    private void dfs(int u, int p) {
        for (int v : g[u]) {
            if (v == p) continue;
            dfs(v, u);
            dp[u] += dp[v];
            weight[u] = Math.max(weight[u], dp[v]);  // 比较所有子树的节点数
        }
        weight[u] = Math.max(weight[u], n - dp[u]);  // 比较父节点方向的节点数
        if (centroid == -1 || weight[centroid] > weight[u]) {
            centroid = u;                              // 最小的最大子树节点数为重心
        }
    }
}
