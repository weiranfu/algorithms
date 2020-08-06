package Graph.ShortestPath.Dijkstra;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

/**
 * 堆优化版的Dijkstra算法往往是 Sparse Graph, 用 Adjacency List 来存图
 * A sparse graph is a graph G = (V, E) in which |E| = O(|V|).
 * A dense graph is a graph G = (V, E) in which |E| = Θ(|V|^2).
 *
 * Every time we pop out a closest node from priority queue,
 * and update its neighbors' distance then add them into priority queue.
 * We simply don't delete the old pair from the queue.
 * As a result a vertex can appear multiple times with different distance in the queue at the same time.
 * O(ElogE)   (when graph is sparse, E = V)
 * (The operation of adding into a pq is O(logV), but we don't delete items in pq, so O(logE))
 */
public class DijkstraSparse {
    class Edge {
        int u; int v; int w;
        Edge(int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
    }

    int s;
    int[] distTo;
    int[] edgeTo;
    boolean[] visited;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);  // int[]{ distance, vertex }
    int INF = 0x3f3f3f3f;

    public DijkstraSparse(List<Edge>[] G, int s) {
        this.s = s;
        int n = G.length;
        distTo = new int[n];
        for (int v = 0; v < n; v += 1)
            distTo[v] = INF;
        edgeTo = new int[n];
        visited = new boolean[n];

        distTo[s] = 0;
        edgeTo[s] = -1;
        pq.add(new int[]{0, s});
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int u = info[1];

            if (visited[u]) continue;           // duplicate nodes in pq.
            visited[u] = true;

            // if (v == target) break;          // must check target here.

            for (Edge e : G[u]) {
                int v = e.v;
                if (!visited[v] && distTo[v] > distTo[u] + e.w) {
                    distTo[v] = distTo[u] + e.w;
                    edgeTo[v] = u;
                    pq.add(new int[]{distTo[v], v});
                }
            }
        }
    }

    /** Returns true if there's a path from s to v. */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /** Finds the shortest path from s to v. */
    public double distTo(int v) {
        return distTo[v];
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
