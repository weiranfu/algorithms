package Graph.ShortestPath.BellmanFord;

import java.util.*;

/** Shortest Path Faster Algorithm(SPFA) is queue based Bellman-Ford.
 *  应用：
 *  存在负权边，求单源最短路
 *  spfa也能解决权值为正的图的最短距离问题，且一般情况下比Dijkstra算法还好
 *  spfa算法更为通用，在求单源最短路的时候，我们可以先考虑spfa算法，如果数据被卡，再考虑实现别的单源最短路算法
 *
 *  The edge weights can be positive, negative, or zero.
 *  The main idea is to create a queue containing only the vertices that were relaxed but that still could further relax their neighbors.
 *  And whenever you can relax some neighbor, you should put it in the queue.
 *
 *  This algorithm can also be used to detect negative cycles as the Bellman-Ford.
 *  We could use cnt[i] to count the number of edges of Shortest Path to vertex i.
 *  If we find the number of edges is greater than or equal to n, which means there must exist a cycle.(Pigeon Hole Principle)
 *  As we are relaxing the vertex, so that cycle must be a negative cycle.
 *  O(E) on average, worst case is O(VE)
 */
public class SPFA {
    int INF = 0x3f3f3f3f;
    int[] dist;
    int[] edgeTo;
    boolean[] inQueue;                      // inQueue[v] = is v currently on the queue?
    Queue<Integer> queue;
    int s;
    int[] cnt;                              // count for detection of negative cycle
    List<int[]>[] g;                        // edge u->v with w. g[u]->int[]{v, w}

    public SPFA(int[][] edges, int n, int s) {
        this.s = s;
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            g[a].add(new int[]{b, w});
        }

        dist = new int[n];
        Arrays.fill(dist, INF);
        edgeTo  = new int[n];
        cnt = new int[n];
        inQueue = new boolean[n];
        queue = new LinkedList<>();

        dist[s] = 0;
        edgeTo[s] = -1;
        queue.add(s);
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    edgeTo[v] = u;

                    cnt[v] = cnt[u] + 1;    // number of edges in SP
                    if (cnt[v] >= n) {      // 某个点的最短路径的边数不能超过 n
                        System.out.println("Negative Cycle found!");
                        return;
                    }

                    if (!inQueue[v]) {          // If is not inQueue
                        queue.add(v);
                        inQueue[v] = true;
                    }
                }
            }
        }
    }

    /** Returns whether there's a path to vertex v. */
    public boolean hasPathTo(int v) {
        return dist[v] < INF;
    }

    /** Finds the shortest path from s to v. */
    public Iterable<Integer> shortestPathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        while (v != -1) {
            path.add(v);
            v = edgeTo[v];
        }
        Collections.reverse(path);
        return path;
    }

}
