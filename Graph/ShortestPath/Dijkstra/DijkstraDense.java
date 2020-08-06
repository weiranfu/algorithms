package Graph.ShortestPath.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Dijkstra's algorithm performs n iterations.
 * A sparse graph is a graph G = (V, E) in which |E| = O(|V|).
 * A dense graph is a graph G = (V, E) in which |E| = Θ(|V|^2).
 *
 * 朴素的Dijkstra算法往往是 Dense Graph，用 Adjacency Matrix 来存储 g[i][j]
 *
 * On each iteration it selects an unmarked vertex v with the lowest value d[v],
 * marks it and checks all the edges (v,to) attempting to improve the value d[to].
 * O(V^2)    (when graph is dense, E = V^2, O(E))
 */
public class DijkstraDense {

    int s;
    int[][] g;
    int[] dist;                // distance to source node
    int[] edgeTo;
    boolean[] visited;
    int INF = 0x3f3f3f3f;

    public DijkstraDense(int[][] edges, int n, int s) {
        this.s = s;
        g = new int[n][n];
        dist = new int[n];
        edgeTo = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            Arrays.fill(g[i], INF);                 // initialize graph and dist
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            g[u][v] = Math.min(g[u][v], w);             // max avoid duplicate edges
        }

        dist[s] = 0;                                  // start point
        edgeTo[s] = -1;
        for (int i = 0; i < n; i++) {                  // find at most n closest nodes

            int u = -1;                                // find the closest node to source
            for (int v = 0; v < n; v++) {
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) {
                    u = v;
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && dist[v] > dist[u] + g[u][v]) {
                    dist[v] = dist[u] + g[u][v];
                    edgeTo[v] = u;
                }
            }
        }
    }

    /** Returns true if there's a path from s to v. */
    public boolean hasPathTo(int v) {
        return dist[v] < INF;
    }

    /** Finds the shortest path from s to v. */
    public List<Integer> shortestPathTo(int v) {
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
