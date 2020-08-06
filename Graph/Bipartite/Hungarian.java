package Graph.Bipartite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hungarian Algorithm  O(VE) in worst case, in practice much faster
 *
 * 一组两两没有公共点的边集 M 称为这张图的 匹配
 * 其中边的数量为 M, 边数最大的 M 为 最大匹配 (maximum matching)
 * 当图中的边带权的时候，边权和最大的为 最大权匹配 。
 * 完美匹配（perfect matching): 所有点都属于匹配，同时也符合最大匹配。
 *
 * 匈牙利算法
 * 从 set1 中遍历各个节点，A 尝试和 set2 中的节点进行匹配。
 * 如果 set2 某一点 B 还没有匹配，则 A 和 B 匹配
 * 如果 set2 某一点 B 已经和 C 匹配了，则让 C 尝试和下一个人匹配
 *
 * 给定一个二分图，其中左半部包含 n1 个点，右半部包含 n2 个点，二分图共包含 m 条边。
 * 请你求出二分图的最大匹配数。
 */
public class Hungarian {

    List<Integer>[] g;                      // store directed path from n1 to n2
    int[] match;                            // store matching info of n2. (which node in n1 that n2 is matching to)
    boolean[] visited;

    public int maxMatch(int n1, int n2, int m, int[][] edges) {
        g = new List[n1];
        match = new int[n2];
        for (int i = 0; i < n1; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = edges[i][0], b = edges[i][1];
            g[a].add(b);
        }

        Arrays.fill(match, -1);         // initialize

        int cnt = 0;
        for (int i = 0; i < n1; i++) {
            visited = new boolean[n2];      // initialize visited array for different n1
            if (find(i)) cnt++;
        }
        return cnt;
    }

    private boolean find(int u) {           // find matching node in n2
        for (int v : g[u]) {
            if (!visited[v]) {              // only try not considered node.
                visited[v] = true;

                if (match[v] == -1 || find(match[v])) { // if v is not matched or we could rematch for match[v]
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
}
