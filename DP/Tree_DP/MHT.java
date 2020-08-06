package DP.Tree_DP;

import java.util.ArrayList;
import java.util.List;

/**
 * Minimum Height Tree
 * For an undirected graph with tree characteristics, we can choose any node as the root.
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 */
public class MHT {

    List<Integer>[] g;
    int[][] dpdown;
    int[] dpup;
    List<Integer> res;
    int min;

    /**
     * 树形DP
     * 从某一点出发 DFS
     * dpup[v] 表示从 v 节点向父节点方向找到的 最长链
     * dpdown[v][0] 和 dp[v][1] 表示从 v 节点向儿子节点方向找到的 最长链 和 次长链
     *
     * dpup[v] 是取 parent 节点up 和 down 路径的最大值，注意区分down的最长链和次长链
     *
     * if v 是 root:
     *      dpup[v] = 0
     * else:
     *      if v 在 parent 的最长链上:   (dpdown[v][0] + 1 == dpdown[p][0])
     *          dpup[v] = max{ dpdown[p][1] + 1, dpup[p] + 1 }
     *      else:
     *          dpup[v] = max{ dpdown[p][0] + 1, dpup[p] + 1 }
     *
     * 对于一个点 v 的最长链, 是 up 和 down 路径的最大值
     * max[v] = max{ dpup[v], dpdown[v][0] }
     *
     * 最后到所有点最长路径最小的点(MHT)最多有两个，且一定在树的直径上
     * 如果最长链是偶数，则有两个，如果是奇数，则只有一个
     */
    public List<Integer> mht_dp(int n, int[][] edges) {
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        dpdown = new int[n][2];
        dpup = new int[n];
        min = n;
        dfsDown(0, -1);
        dfs(0, -1);
        return res;
    }
    private void dfsDown(int v, int p) {
        for (int w : g[v]) {
            if (w == p) continue;
            dfsDown(w, v);
            if (dpdown[v][0] < dpdown[w][0] + 1) {
                dpdown[v][1] = dpdown[v][0];
                dpdown[v][0] = dpdown[w][0] + 1;
            } else if (dpdown[v][1] < dpdown[w][0] + 1) {
                dpdown[v][1] = dpdown[w][0] + 1;
            }
        }
    }
    private void dfs(int v, int p) {
        if (p != -1) {
            int up = dpup[p] + 1;                   // dpup[p] + 1
            if (dpdown[v][0] + 1 == dpdown[p][0]) { // 判断u 是否在 dpdown[p] 上
                up = Math.max(up, dpdown[p][1] + 1);
            } else {
                up = Math.max(up, dpdown[p][0] + 1);
            }
            dpup[v] = up;
        }
        int max = Math.max(dpdown[v][0], dpup[v]); // 最长链
        if (min > max) {
            min = max;
            res = new ArrayList<>();
            res.add(v);
        } else if (min == max) {
            res.add(v);
        }
        for (int w : g[v]) {
            if (w == p) continue;
            dfs(w, v);
        }
    }


    /**
     * 贪心
     * 两次DFS找到直径
     * 返回直径的中心点（一个或两个）
     */
    int[] edgeTo;
    int max;
    int node;

    public List<Integer> mht_dfs(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        g = new List[n];
        edgeTo = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        max = 0;
        dfs(0, -1, 0);
        max = 0;
        dfs(node, -1, 0);

        List<Integer> diameter = new ArrayList<Integer>();
        int curr = node;
        while (curr != -1) {
            diameter.add(curr);
            curr = edgeTo[curr];
        }
        int size = diameter.size();
        if (size % 2 == 0) {
            res.add(diameter.get(size / 2));
            res.add(diameter.get(size / 2 - 1));
        } else {
            res.add(diameter.get(size / 2));
        }
        return res;
    }
    private void dfs(int v, int p, int len) {
        if (len > max) {
            max = len;
            node = v;
        }
        edgeTo[v] = p;
        for (int w : g[v]) {
            if (w == p) continue;
            dfs(w, v, len + 1);
        }
    }

}
