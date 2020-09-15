package Graph.MST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dijkstra算法是更新到起始点的距离，Prim是更新到集合S的距离
 *
 * For every not yet selected vertex we will store the minimum edge to an already selected vertex.
 * After adding an edge some minimum edge pointers have to be recalculated. Note that the weights only can decrease,
 * i.e. the minimal weight edge of every not yet selected vertex might stay the same,
 * or it will be updated by an edge to the newly selected vertex.
 * O(V^2 + E)    (when graph is dense, V^2 = E, O(E))
 * 稀疏图用Kruskal算法，稠密图用朴素版Prim算法，堆优化版Prim算法一般不会用
 */
public class PrimMST_Dense {

    int n;
    int[][] g;               // Dense graph using Adjacency Matrix
    int[] dist;              // dist to MST
    boolean[] visited;
    int[][] edgeTo;
    int weight;
    int INF = 0x3f3f3f3f;

    public PrimMST_Dense(int n, int[][] edges) {
        this.n = n;
        dist = new int[n];
        g = new int[n][n];
        visited = new boolean[n];
        edgeTo = new int[n][2];             // node u -> int[]{v, w}
        for (int i = 0; i < n; i++) {
            dist[i] = INF;                  // init dist and graph
            Arrays.fill(g[i], INF);
            edgeTo[i] = new int[]{-1, -1};
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            g[u][v] = Math.min(g[u][v], w);
            g[v][u] = Math.min(g[v][u], w);
        }

        weight = 0;
        for (int i = 0; i < n; i++) {       // connect n nodes
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) {
                    u = v;
                }
            }

            if (i != 0 && dist[u] == INF) {
                System.out.println("No MST!!");
                return;
            }

            visited[u] = true;
            if (i != 0) weight += dist[u];

            for (int v = 0; v < n; v++) {
                if (!visited[v] && dist[v] > g[u][v]) {  // the distance to set is g[u][v]
                    dist[v] = g[u][v];
                    edgeTo[v] = new int[]{u, g[u][v]};
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
