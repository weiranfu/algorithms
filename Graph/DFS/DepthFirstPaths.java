package Graph.DFS;

import Graph.Graph;

import java.util.List;
import java.util.ArrayList;

/**
 * DFS 与 BFS
 * DFS -> 连通性，BFS -> 最短路
 *    目标深度浅但待搜素范围大的情况，选择用 DFS
 * DFS 在搜索时，不用考虑call stack里面的点(即不能往回搜)
 *
 * BFS
 * 空间是指数级别的，大
 * 不会有爆栈风险
 * 可以求最短，最小
 *
 * DFS
 * 空间和深度成正比，小！
 * 有爆栈的风险，比如深度最坏可能有1e5层，会爆栈（C++一般4M）；层信息都放在栈空间里
 * 不能搜最短、最小
 *
 * 1. 迷宫图
 *    1.1 最短路，如果用 DFS 则效率差很多
 *      虽然我们可以找到终点，但是不一定是最短路
 *    1.2 通路问题
 *        只用搜到一条可以到终点的路径即可
 *    1.3 求连通分量
 *        把一个点附近所有相连的点染色，即为一个连通分量
 * 2. 树
 *    pre-order/in-order/post-order traversal
 * 3. 图论
 * 4. 隐式图搜索
 *    从起始状态，动态生成并转移到新状态。(不能遍历所有状态)
 * 5. 枚举
 *    排列，组合，子集
 */
public class DepthFirstPaths {
    private boolean[] visited;
    private int[] edgeTo;
    private int s;

    /** Finds all paths from Graph G using dfs. */
    public DepthFirstPaths(Graph G, int s) {
        visited = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        edgeTo[s] = -1; // There's no edge to s.
        dfs(G, s);
    }

    /** Finds vertices connected to v. */
    private void dfs(Graph G, int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /** Returns true if there's a path from s to v. */
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    /** Returns the path from s to v if there exists. */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        while (v != s) {
            path.add(0, v);
            v = edgeTo[v];
        }
        path.add(0, s);
        return path;
    }
}
