package Graph;

/** The Edge class represents a weighted edge in an EdgeWeightedGraph.
 *  Each edge consists of two vertices and a weight.
 */
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final int weight;

    /**
     * @param v one vertex
     * @param w the other vertex
     * @param weight weight of this edge
     */
    public Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /** Returns the weight of the Edge. */
    public int weight() {
        return weight;
    }

    /** Returns either endpoint of this edge. */
    public int either() {
        return v;
    }

    /** Returns the endpoint of this edge which is different from this given vertex. */
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public int compareTo(Edge that) {
        // Using Double.compare() to compare two double values.
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
