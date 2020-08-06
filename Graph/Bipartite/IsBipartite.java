package Graph.Bipartite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 节点由两个集合组成，且两个集合内部没有边的图。
 * 如果两个集合中的点分别染成黑色和白色，可以发现二分图中的每一条边都一定是连接一个黑色点和一个白色点。
 *
 * 二分图不存在长度为奇数的环
 * 因为每一条边都是从一个集合走到另一个集合，只有走偶数次才可能回到同一个集合。1 -> 2 -> 1 -> 2 -> 1
 * 如果发现了奇环，那么就不是二分图，否则是。
 *
 * 染色法 Bi-color  O(V + E)
 * 如果一条边两个端点 A, B 有 A 在 set1 里，那 B 一定在 set2 里。
 * 由于图中没有奇数环，所以染色一定是没有矛盾的。
 * DFS 给所有点染色 1 或 2，如果发现有矛盾则不存在二分图
 */
public class IsBipartite {

    int n;
    List<Integer>[] g;
    int[] color;

    public boolean isBipartite(int[][] edges, int n) {
        this.n = n;
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        color = new int[n];

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {                // is not colored
                if (!dfs(i, 1)) {               // colored with 1
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int u, int c) {
        color[u] = c;
        for (int v : g[u]) {
            if (color[v] == 0) {
                if (!dfs(v, 3 - c)) {       // if c == 1, then color 2
                    return false;              // if c == 2, then color 1
                }
            } else if (color[v] == c) return false; // find same color of two ends of an edge
        }
        return true;
    }


    /* *************** BFS ********************** */

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {                    // find a node without coloring
                color[i] = 1;
                q.add(i);
                while (!q.isEmpty()) {
                    int u = q.poll();
                    for (int v : graph[u]) {
                        if (color[v] == 0) {
                            color[v] = 3 - color[u]; // choose other color
                            q.add(v);
                        } else if (color[v] == color[u]) return false;
                    }
                }
            }
        }
        return true;
    }
}
