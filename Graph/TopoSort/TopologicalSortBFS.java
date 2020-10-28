package Graph.TopoSort;

import Graph.*;
import java.util.*;

/** 若一个由图中所有点构成的序列A满足：对于图中的每条边(x, y)，x在A中都出现在y之前，则称A是该图的一个拓扑序列。
 * 应用
 * 1. 拓扑排序
 * 2. detect cycle  (环会被剩下来)
 *
 * Using BFS to topological sort a directed acyclic graph.
 * 1. Calculate in-degree of all vertices.
 * 2. Pick any vertex v which has in-degree of 0.
 * 3. Add v to our topological sort list. Remove the vertex v and all edges coming out of it.
 *    Decrement in-degrees of all neighbors of vertex v by 1.
 * 4. Repeat steps 2 and 3 until all vertices are removed.
 *
 * O(V + E)
 */
public class TopologicalSortBFS {
    List<Integer> res;
    int[] indegree;
    Queue<Integer> queue;
    int n;

    public TopologicalSortBFS(EdgeWeightedDigraph DAG) {
        n = DAG.V();
        res = new ArrayList<>();
        indegree = new int[n];
        queue = new LinkedList<>();              // store vertices whose indegree is 0.
        for (int v = 0; v < n; v++) {
            indegree[v] = DAG.indegree(v);
            if (DAG.indegree(v) == 0) {
                queue.add(v);
            }
        }
        // start bfs for all vertices in start array.
        while (!queue.isEmpty()) {
            int v = queue.poll();
            res.add(v);
            for (DirectedEdge e : DAG.adj(v)) {
                int w = e.to();
                indegree[w]--;
                if (indegree[w] == 0) {
                    queue.add(w);
                }
            }
        }
    }

    public Iterable<Integer> sort() {
        if (res.size() != n) {
            System.out.println("Cycle exists!!!");
            return new ArrayList<>();
        }
        return res;
    }
}
