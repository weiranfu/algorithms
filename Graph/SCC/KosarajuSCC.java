package Graph.SCC;

import Graph.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reverse graph: Strong Components in G are same.
 * Kernel DAG: Contract each strong component into a sing vertex
 * Idea: 1. compute topological order (post-order) in reversed kernel DAG
 *       2. Run DFS considering vertices in reverse topological order (reverse post-order) in original DAG.
 */
public class KosarajuSCC {
    List<Integer> topo;
    boolean[] marked;
    int[] ids;
    int count;

    public KosarajuSCC(EdgeWeightedDigraph g) {
        int n = g.V();
        marked = new boolean[n];
        topo = new ArrayList<>();
        ids = new int[n];
        count = 0;

        // Reverse graph
        EdgeWeightedDigraph newG = new EdgeWeightedDigraph(n);
        for (DirectedEdge e : g.edges()) {
            int from = e.from();
            int to = e.to();
            newG.addEdge(new DirectedEdge(to, from, e.weight()));
        }

        // Get topological order(post-order)
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                topological(i, newG);
            }
        }

        // Reverse post-order
        Collections.reverse(topo);

        // Find SCC
        marked = new boolean[n];        // clear mark
        for (int i = 0; i < n; i++) {
            int vertex = topo.get(i);
            if (!marked[vertex]) {
                dfs(vertex, g);
                count++;               // SCC count++
            }
        }

    }

    private void topological(int v, EdgeWeightedDigraph g) {
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int to = e.to();
            if (!marked[to]) {
                topological(to, g);
            }
        }
        topo.add(v);                // post-order
    }

    private void dfs(int v, EdgeWeightedDigraph g) {
        marked[v] = true;
        ids[v] = count;                      // mark as count id.
        for (DirectedEdge e : g.adj(v)) {
            int to = e.to();
            if (!marked[to]) {
                dfs(to, g);
            }
        }
    }

    public int countStronglyConnectedComponents() {
        return count;
    }

    public boolean stronglyConnected(int v, int w) {
        return ids[v] == ids[w];
    }

    public int getId(int v) {
        return ids[v];
    }
}
