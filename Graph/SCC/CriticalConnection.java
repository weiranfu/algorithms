package Graph.SCC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a graph of nodes connected with undirected path.
 *
 * A critical connection is a connection that, if removed, will make some node unable to reach some other nodes.
 *
 * Return all critical connections in the graph in any order.
 */
public class CriticalConnection {
    List<Integer>[] g;
    int n;
    int id;
    int[] ids, low;
    boolean[] onStack;
    List<List<Integer>> res;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.n = n;
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        id = 1;
        ids = new int[n];
        low = new int[n];
        onStack = new boolean[n];

        for (List<Integer> edge : connections) {
            int a = edge.get(0), b = edge.get(1);
            g[a].add(b);
            g[b].add(a);
        }
        res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (ids[i] == 0) {
                dfs(i, -1);
            }
        }
        return res;
    }
    private void dfs(int u, int p) {
        ids[u] = low[u] = id++;
        onStack[u] = true;
        for (int v : g[u]) {
            if (v == p) continue;
            if (ids[v] == 0) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], ids[v]);
            }
        }
        // u - v is critical, there is no path for v to reach back to u or previous vertices of u
        if (low[u] == ids[u] && p != -1) {
            res.add(Arrays.asList(p, u));
        }
        onStack[u] = false;
    }
}
