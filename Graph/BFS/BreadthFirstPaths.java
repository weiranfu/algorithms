package Graph.BFS;

import Graph.Graph;

import java.util.*;

/**
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
 * O(V + E)
 * BFS 常使用于寻找 最短路(无权重) 及层序遍历时
 * 1. 迷宫图 (最短)
 * 2. 图论   (最短，连通性)
 * 3. 树   (level order)
 * 4. 隐式图搜索  (最短)
 *    (state1 -> state2) 一般不会枚举所有的state，而是根据现有的state进行转移
 * 5. Topological sort
 * 6. 分层图 (按到终点的距离分层，再从起点出发，枚举所有到终点的最短路）
 */
public class BreadthFirstPaths {
    private boolean[] visited;
    private int[] edgeTo;
    private int s;
    Queue<Integer> queue = new LinkedList<>();

    /** Finds all paths in Graph G using bfs. */
    public BreadthFirstPaths(Graph G, int s) {
        visited = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;

        edgeTo[s] = -1;
        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (visited[w]) continue;
                visited[w] = true;         // marked visited before adding to queue
                edgeTo[w] = v;             // so that the neighbors in same level won't add it twice
                queue.add(w);
            }
        }
    }

    /** Returns true if there's a path from s to v. */
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    /** Finds the shortest path from s to v. */
    public Iterable<Integer> shortestPathTo(int v) {
        List<Integer> path = new ArrayList<>();
        if (!hasPathTo(v)) return path;

        while (v != -1) {
            path.add(v);
            v = edgeTo[v];
        }

        Collections.reverse(path);
        return path;
    }
}
