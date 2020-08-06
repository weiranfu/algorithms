package Graph;

import java.util.List;
import java.util.ArrayList;

/** The EdgeWeightedDigraph class represents a edge-weighted
 *  undirected graph of vertices named 0 through V - 1, where each
 *  undirected edge is of type Edge and has a real-valued weight.
 *  This implementation uses an adjacency-lists representation, which
 *  is a vertex-indexed array of list objects.
 */
public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int v = 0; v < V; v += 1) {
            adj[v] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E += 1;
    }

    /** Returns the edges incident on vertex. */
    public List<Edge> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

    /** Returns all edges in this edge-weighted graph.
     * A self-loop cycle may be in the graph.
     */
    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        for (int v = 0; v < V; v += 1) {
            int selfLoops = 0;
            for (Edge e : adj[v]) {
                // Only add an edge once when we visits the smaller vertex of an edge.
                if (e.other(v) > v) {
                    edges.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        edges.add(e);
                    }
                    selfLoops += 1;
                }
            }
        }
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
