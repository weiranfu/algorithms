package Set.DisjointSets;
/**
 * A Disjoint-Sets (or Union-Find) data structure that
 * keeps track of a fixed number of elements partitioned into a number of disjoint sets.
 */
public interface DisjointSet {
     /** Connect x and y. Also known as union. */
     void connect(int x, int y);

     /** Checks to see if two items are connected. */
     boolean isConnected(int x, int y);
}
