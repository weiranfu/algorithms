package Graph.TopoSort;

import Graph.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/** 若一个由图中所有点构成的序列A满足：对于图中的每条边(x, y)，x在A中都出现在y之前，则称A是该图的一个拓扑序列。
 * Using DFS for all vertices to get the topological sort of Directed Acyclic Graph.
 */
public class TopologicalSortDFS {
    /*
        visited
        0: not visited yet
        1: visiting now
        2: visited already
     */
    int[] visited;
    List<Integer> postOrder;
    int n;

    public TopologicalSortDFS(EdgeWeightedDigraph DAG) {
        n = DAG.V();
        visited = new int[n];
        postOrder = new ArrayList<>();
        for (int v = 0; v < n; v += 1) {
            if (visited[v] == 0) {
                if (!dfs(DAG, v)) {
                    postOrder = new ArrayList<>();  // clear post-order list
                    break;
                }
            }
        }
        Collections.reverse(postOrder);
    }

    /** Post-order traversal DFS from vertex v and mark all visited vertices. */
    private boolean dfs(EdgeWeightedDigraph DAG, int v) {

        visited[v] = 1;                             // visiting

        for (DirectedEdge e : DAG.adj(v)) {
            int w = e.to();
            if (visited[w] == 0) {
                if (!dfs(DAG, w)) {
                    return false;
                }
            }
            if (visited[w] == 1) {
                System.out.println("Cycle exists!!!");
                return false;
            }
        }

        visited[v] = 2;                             // visited
        postOrder.add(v);                           // after dfs all edges then add v
        return true;
    }

    public Iterable<Integer> topologicalSort() {
        return postOrder;
    }
}
