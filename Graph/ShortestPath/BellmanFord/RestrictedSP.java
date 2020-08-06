package Graph.ShortestPath.BellmanFord;

import java.util.Arrays;

/**
 * 有边数限制的最短路
 *
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你求出从1号点到n号点的最多经过k条边的最短距离，如果无法从1号点走到n号点，输出impossible。
 * 注意：图中可能 存在负权回路 。
 *
 * 因为可能有负边和负环，且最短路最多只能有 K 条边，这题可以用 Bellman Ford
 *
 * 迭代 K 次，且每次从上一次迭代结果relax 即能保证最短路最多只有 K 条边, 需要 backup 滚动数组存上一次迭代结果
 * 注意一定是从上一次迭代结果relax，不能根据 edges relax 顺序在一次迭代中多次relax
 * 例如 edge{u, v, w}: {1, 2, 1}, {2, 3, 1}, {1, 3, 3} 从 1 到 3 最多经过 1 条边的最短路是 3
 * 但如果我们迭代一次并按照edges顺序relax的话，会发现
 * point        1       2       3
 * 0            0       INF     INF
 * 1            0       1       2(1 + 1)
 * 如果我们迭代一次并按照edges顺序根据上一次迭代结果relax的话
 * 0            0       INF     INF
 * 1            0       1       3(0 + 3)
 *
 * 最后判断是否找到了这样的最段距离不能用 dist[n-1] == INF ? impossible : dist[n-1]
 * 因为可能存在负边，即使我们没有找到 K 条边的最短路径，
 * 但 INF - w < INF 仍然会被relax，所有用 dist[n-1] > INF / 2 ? impossible : dist[n - 1]
 */
public class RestrictedSP {
    int n, m, k;
    int N = 505, M = 10005;
    int[] dist = new int[N];
    int INF = 0x3f3f3f3f;

    public void restrictedSP(int n, int[][] edges, int k) {
        this.n = n; m = edges.length; this.k = k;
        Arrays.fill(dist, INF);
        dist[0] = 0;
        for (int i = 0; i < k; i++) {                                           // iterate K times
            int[] backup = Arrays.copyOf(dist, n);                              // copy backup array
            for (int j = 0; j < m; j++) {
                int u = edges[j][0], v = edges[j][1], w = edges[j][2];
                dist[v] = Math.min(dist[v], backup[u] + w);                     // relax based on backup
            }
        }
        if (dist[n - 1] > INF / 2) {                                          // 注意如果有负边的话，dist[v] < INF 是可能的
            System.out.println("impossible");
        } else {
            System.out.println(dist[n - 1]);
        }
    }
}
