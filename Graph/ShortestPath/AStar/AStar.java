package Graph.ShortestPath.AStar;

import Graph.*;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

/**
 * A* algorithm: cannot apply to negative path graph
 * value = cost + heuristic
 * f(n) = g(n) + h(n)
 * There're two sets: Open set and close set
 * We maintain the open set using a priority queue with f(n) as priority.
 * Every time we pop out the smallest f(n) and add it into close set(never update).
 * Check its neighbours, if the neighbour is not visited, add into open set.
 * If the neighbour is already in open set, we check whether cost g(n) can be updated,
 * if distance becomes smaller, we add it into open set.(There could be duplicate nodes in
 * open set. So we need to check when we pop out nodes)
 * So we use three digit to represent states of node:
 * 0: not visited yet
 * 1: in the open set
 * 2: in the close set
 *
 * Here in a graph with nodes, we could use the min edge weight to neighbors as heuristic value
 */
public class AStar {
    class Node implements Comparable<Node> {
        int v;
        double f;        // value (f) = cost (g) + heuristic value (h)
        Node(int v, double f) {
            this.v = v; this.f = f;
        }
        @Override
        public int compareTo(Node n) {
            return Double.compare(f, n.f);
        }
        @Override
        public String toString() {
            return v + ":" + f;
        }
    }

    int s, t, n;
    int[] edgeTo;
    double[] dist;
    int[] visited;   // 0: not visited, 1: open list, 2: close list
    double[] heuristic;
    PriorityQueue<Node> pq;

    public AStar(EdgeWeightedDigraph dg, int source, int target) {
        this.s = source;
        this.t = target;
        int n = dg.V();
        edgeTo = new int[n];
        dist = new double[n];
        visited = new int[n];                       // default 0

        heuristic = new double[n];
        for (int u = 0; u < n; u++) {               // get the heuristic value
            double min = Double.MAX_VALUE;
            for (DirectedEdge e : dg.adj(u)) {
                min = Math.min(min, e.weight());
            }
            heuristic[u] = min;
        }
        heuristic[t] = 0;                           // end point's heuristic value is 0

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0.0;
        edgeTo[s] = -1;
        pq.add(new Node(s, heuristic[s]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v;
            if (visited[u] == 2) continue;         // avoid duplicates node as dijkstra
            visited[u] = 2;
            if (u == t) {                      // find the end
                break;
            }
            for (DirectedEdge e : dg.adj(u)) {
                int v = e.to();
                if (visited[v] == 2) continue;     // if visited
                if (visited[v] == 0 || Double.compare(dist[v], dist[u] + e.weight()) > 0) {
                    dist[v] = dist[u] + e.weight();
                    edgeTo[v] = u;
                    visited[v] = 1;               // mark as in open list
                    pq.add(new Node(v, dist[v] + heuristic[v]));   // add f = cost + h
                }
            }
        }
    }

    /**
     * There could be many heuristic function.
     * 1. Manhattan distance:
     *      (i, j) -> (x, y)
     *      Math.abs(i - x) + Math.abs(j - y)
     * 2. Euclidean distance:
     *      Math.sqrt((i-x)*(i-x) + (j-y)*(j-y))
     * 3. Chebyshev distance
     *      Math.max(Math.abs(i-x), Math.abs(j-y))     // 8 个方向都以走的情况，包括斜线
     *
     * Here in a graph with nodes, we could use the min edge weight to neighbors as heuristic value
     */
    private double h(int v) {
        return heuristic[v];
    }

    /** Finds the shortest path from s to v. */
    public Iterable<Integer> shortestPath() {
        List<Integer> path = new ArrayList<>();
        int v = t;
        while (v != -1) {
            path.add(v);
            v = edgeTo[v];
        }
        Collections.reverse(path);
        return path;
    }
}
