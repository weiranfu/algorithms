package Graph;

import java.util.List;
import java.util.ArrayList;

/** The EdgeWeightedDigraph class represents a edge-weighted
 *  directed graph of vertices named 0 through V - 1, where each
 *  directed edge is of type DirectedEdge and has a real-valued weight.
 *  This implementation uses an adjacency-lists representation, which
 *  is a vertex-indexed array of list objects.
 */
public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;    // adj[v] = adjacency list of edges for vertex v
    private int[] indegree;              // indegree[v] = indegree of vertex v

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<DirectedEdge>[]) new ArrayList[V];
        for (int i = 0; i < V; i += 1) {
            adj[i] = new ArrayList<>();
        }
        indegree = new int[V];
    }

    /** Returns number of vertices. */
    public int V() {
        return V;
    }

    /** Returns number of edges. */
    public int E() {
        return E;
    }

    /** Adds the directed edge e to this edge-weighted digraph. */
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        indegree[w] += 1;
        E += 1;
    }

    /** Returns the directed edges incident from vertex. */
    public List<DirectedEdge> adj(int v) {
        return adj[v];
    }

    /** Returns the number of directed edges in to vertex. */
    public int indegree(int v) {
        return indegree[v];
    }

    /** Returns the number of directed edges out from vertex. */
    public int outdegree(int v) {
        return adj[v].size();
    }


    public List<DirectedEdge> edges() {
        List<DirectedEdge> list = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Vertices: " + V + ", Edges: " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
