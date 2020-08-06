package Graph.MST;

import Graph.*;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

/** Get the Minimum Spanning Tree(MST) using Prime's algorithm
 * in an edge-weighted graph. The edge weights can be positive,
 * zero, or negative and need not be distinct.
 * Prime's algorithm: Every time we add a new vertex to MST, we consider
 * the lightest cutting edge which connects to a vertex in MST.
 * If a vertex not in MST has multiple edges connecting to MST, then
 * we only consider the lightest cutting edge it has, which means a vertex
 * not in MST has only one possible cutting edge.
 * O(ElogE + E)   (when graph is sparse, E = V)
 * 稀疏图用Kruskal算法，稠密图用朴素版Prim算法，堆优化版Prim算法一般不会用
  */
public class PrimeMST_Sparse {
    int n;
    int[][] edgeTo;             // the shortest edge to a vertex.
    int[] dist;               // the distance from MST to a vertex.
    boolean[] visited;
    int weight;               // total weight of MST
    int INF = 0x3f3f3f3f;

    public PrimeMST_Sparse(List<int[]>[] g) {
        n = g.length;
        edgeTo = new int[n][2];
        dist = new int[n];
        visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Arrays.fill(dist, INF);
        for (int i = 0; i < n; i++) {
            edgeTo[i] = new int[]{-1, -1};
        }

        weight = 0;
        pq.add(new int[]{dist[0], 0}); // starts from 0
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int u = info[1];

            if (visited[u]) continue;
            visited[u] = true;
            if (u != 0) weight += dist[u];

            for (int[] edge : g[u]) {
                int v = edge[0], w = edge[1];
                if (!visited[v] && dist[v] > w) {
                    dist[v] = w;
                    edgeTo[v] = new int[]{u, w};
                    pq.add(new int[]{w, v});
                }
            }
        }
    }

    public double weight() {
        return weight;
    }

    public Iterable<int[]> edges() {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (edgeTo[i][0] == -1) continue;
            edges.add(new int[]{i, edgeTo[i][0], edgeTo[i][1]});
        }
        return edges;
    }
}
