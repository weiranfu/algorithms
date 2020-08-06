package Graph;

import java.util.List;
import java.util.ArrayList;
/**
 * This is a Graph class with some common APIs.
 * This Graph uses Adjacent List as data structure to store neighbors of a vertex.
 */
public class Graph {
    private final int V;
    private List<Integer>[] adj;   // adjacent list of vertices for vertex
    private int E;

    /** Creates empty graph with v vertices. */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        // adj is an array of integer lists.
        adj = (List<Integer>[]) new ArrayList[V];   // Initialize an array of ArrayList using ArrayList[capacity].
        for (int v = 0; v < V; v += 1) {
            adj[v] = new ArrayList<Integer>();
        }
    }

    /** Adds an edge v-w. */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E += 1;
    }

    /** Returns all vertices adjacent to v. */
    public Iterable<Integer> adj(int v) {
       return adj[v];
    }

    /** Returns number of vertices. */
    public int V() {
        return V;
    }

    /** Returns number of edges. */
    public int E() {
        return E;
    }

    /** Returns degree of vertex v in this graph. */
    public int degree(int v) {
        int degree = 0;
        for (int w : this.adj(v)) {
            degree += 1;
        }
        return degree;
    }

    /** Prints all the edges in this graph. */
    public void print() {
        for (int v = 0; v < this.V(); v += 1) {
            for (int w : this.adj(v)) {
                System.out.println(v + "-" + w);
            }
        }
    }
}
