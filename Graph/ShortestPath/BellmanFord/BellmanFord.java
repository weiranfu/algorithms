package Graph.ShortestPath.BellmanFord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 应用
 * 1. 处理有负权边的图
 * 2. 循环次数的含义：循环K次后，表示不超过K条边的最短距离
 *    有边数限制的最短路，只能用Bellman-Ford算法，不能用SPFA算法
 * 3. 如果有负权回路，最短路不一定存在
 *    Bellman-Ford算法可以求出是否有负环
 *
 * Bellman-Ford algorithm will try to relax all edges (V - 1) times.
 * 每迭代一次，就能多往外扩展一层确定的最短路。
 * 如果只循环 K 次，我们就能找到不超过 K 条边的最短路。
 * V 个点的最短路最长只有 V - 1，所有循环 V - 1 次就能找到所有的最短路。
 * If there exist a negative cycle, we will relax forever.
 * So if we relax on the |V|th time, there must exist a negative cycle，因为我们找到了一条路径距离为 V 的最短路，必然有环
 * Time complexity: O(VE)
 */
public class BellmanFord {
    int[] dist;               // distTo[v] = distance  of shortest s->v path
    int[] edgeTo;               // edgeTo[v] = last edge on shortest s->v path
    int s;                         // source vertex
    int INF = 0x3f3f3f3f;

    public BellmanFord(int[][] edges, int n, int s) {
        dist = new int[n];
        edgeTo = new int[n];
        this.s = s;
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
        }

        dist[0] = 0;
        edgeTo[0] = -1;
        int x = -1;                        // 如果第 V 次循环还有 relax 更新并改变了 x 的话说明有负环
        for (int i = 0; i < n; i++) {      // Relax all edges V - 1 times.
            x = -1;
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    edgeTo[v] = u;
                    x = u;
                }
            }
        }
        if (x != -1) {
            System.out.println("Negative cycle found!");
        }
    }

    /** Returns whether there's a path to vertex v. */
    public boolean hasPathTo(int v) {
        return dist[v] < INF / 2;               // 注意如果有负边的话，dist[v] < INF 是可能的
    }

    /** Finds the shortest path from s to v. */
    public Iterable<Integer> shortestPathTo(int v) {
        List<Integer> path = new ArrayList<>();
        if (!hasPathTo(v)) return path;
        while (v != -1) {
            path.add(v);
            v = edgeTo[v];
        }
        Collections.reverse(path);
        return path;
    }
}
