package Graph;

/** The DirectedEdge class represents a weighted edge in an EdgeWeightedDigraph.
 * Each edge consists of two vertices and a weight.
 */
public class DirectedEdge  {
    private final int v;
    private final int w;
    private final double weight;

    /**
     * @param v the tail vertex
     * @param w the head vertex
     * @param weight the weight of this edge
     */
    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /** Returns the tail vertex of the directed edge. */
    public int from() {
        return v;
    }

    /** Returns the head vertex of the directed edge. */
    public int to() {
        return w;
    }

    /** Returns the weight of this edge. */
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }


}
