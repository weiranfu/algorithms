package Graph.ShortestPath.BellmanFord;

import java.util.*;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你判断图中是否存在负权回路。
 * 注意是找所有可能的负环，所有我们最开始要把所有的点加入Queue
 */
public class SPFA_DetectNegativeCycle {
    int[] dist;
    boolean[] inQueue;
    Queue<Integer> queue;
    int s;
    int[] cnt;                              // count for detection of negative cycle
    List<int[]>[] g;

    public boolean detectNegCycle(int[][] edges, int n, int s) {
        this.s = s;
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            g[a].add(new int[]{b, w});
        }

        dist = new int[n];
        cnt = new int[n];
        inQueue = new boolean[n];
        queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {           // add all nodes into queue
            queue.add(i);
            inQueue[i] = true;
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;

                    cnt[v] = cnt[u] + 1;    // number of edges in SP
                    if (cnt[v] >= n) {      // 某个点的最短路径的边数不能超过 n
                        System.out.println("Negative Cycle found!");
                        return true;
                    }

                    if (!inQueue[v]) {          // If is not inQueue
                        queue.add(v);
                        inQueue[v] = true;
                    }
                }
            }
        }
        System.out.println("No negative cycle exists");
        return false;
    }
}
