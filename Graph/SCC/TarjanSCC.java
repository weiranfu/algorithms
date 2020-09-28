package Graph.SCC;

import java.util.List;
import java.util.Stack;

/** Tarjan is used to find/count the Strongly Connected Components (SCCs) in a directed graph in O(V+E). */

public class TarjanSCC<T> {
    int n;
    List<Integer>[] g;
    int id = 1;  // id is assigned to each node when visited.
    int[] ids;     // Store id for each node.
    int[] low;     // Store lowest id a node can reach. (low-link value)
    Stack<Integer> stack = new Stack<>();
    boolean[] onStack;       // determine whether a node is onStack.
    int[] scc;       // SCC id
    int[] size;      // each SCC size
    int scc_cnt = 0; // count for SCC


    public TarjanSCC(List<Integer>[] g) {
        this.g = g;
        n = g.length;
        ids = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        for (int i = 0; i < n; i += 1) {
            if (ids[i] == 0) {
                dfs(i);
            }
        }
    }

    // Tarjan dfs visit vertex v and update low-link values
    private void dfs(int u) {
        ids[u] = low[u] = id++;
        stack.push(u);
        onStack[u] = true;
        for (int v : g[u]) {
             if (ids[v] == 0) {        // if not visit
                 dfs(v);
                 low[u] = Math.min(low[u], low[v]);
             } else if (onStack[v]) {
                 low[u] = Math.min(low[u], ids[v]);
             }
        }
        // After dfs all neighbors of node v, pop out all nodes in stack.
        // if v is the root node of SCC
        if (ids[u] == low[u]) { // Find the smallest node in a cycle.
            scc_cnt++;
            while (!stack.isEmpty()) {
                int v = stack.pop();
                onStack[v] = false;
                scc[v] = scc_cnt;
                size[scc_cnt]++;
                if (v == u) break;   // Pop out nodes in stack until the smallest node in a cycle.
            }
        }
    }

    public int countStronglyConnectedComponents() {
        return scc_cnt;
    }

    public boolean stronglyConnected(int v, int w) {
        return scc[v] == scc[w];
    }
}
