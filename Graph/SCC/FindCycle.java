package Graph.SCC;

import Graph.Edge;
import Graph.Graph;

/**
 * We will run a series of DFS in the graph. Initially all vertices are colored white (0).
 * From each unvisited (white) vertex, start the DFS, mark it gray (1) while entering and mark it black (2) on exit.
 * If DFS moves to a gray vertex, then we have found a cycle
 */
public class FindCycle {

    int[] color;
    int[] edgeTo;
    int cycleStart = -1, cycleEnd = -1;

    public FindCycle(Graph G) {
        int n = G.V();
        color = new int[n];
        edgeTo = new int[n];

        for (int v = 0; v < n; v++) {
            if (color[v] == 0) {
                if (findCycle(v, G)) {
                    break;
                }
            }
        }
        if (cycleStart != -1) {
            System.out.println("Find Cycle!");
        }
    }

    private boolean findCycle(int v, Graph G) {
        color[v] = 1;                     // on stack
        for (int w : G.adj(v)) {
            if (color[w] == 0) {
                if (findCycle(w, G)) {
                    return true;
                }
            } else if (color[w] == 1) {
                cycleStart = w;
                cycleEnd = v;
                return true;
            }
        }
        color[v] = 2;
        return false;
    }
}
