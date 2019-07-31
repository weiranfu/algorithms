package DisjointSets;
/**
 * connect() is O(lgn), isConnected() is O(lgn).
 * Using size of set (number of items in set) to represent weight.
 */
public class WeightedQuickUnionDS implements DisjointSet {
    int[] parent;

    /** Creates a UnionFind data structure holding N vertices.
     * Initially, all vertices are in disjoint sets. */
    public WeightedQuickUnionDS(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i += 1) {
            parent[i] = -1;              // Initially each set is one size.
        }
    }

    /** Throws an exception if v is not a valid index. */
    public void validate(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException(v + " is not a valid index.");
        }
    }

    /** Returns the size of the set v belongs to. */
    public int sizeOf(int v) {
        return - parent(find(v));
    }

    /** Returns the parent of v.
     * If v is the root of a tree, return the negative size of the tree.
     */
    public int parent(int v) {
        return parent[v];
    }

    /** Returns true if nodes v1 and v2 are connected. */
    @Override
    public boolean isConnected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /**
     * Connects two elements v1 and v2 together. v1 and v2 can be any valid
     * elements, and a union-by-size heuristic is used. If the sizes of the sets
     * are equal, tie break by connecting v1's root to v2's root. Union a
     * vertex with itself or vertices that are already connected should not
     * change the sets but may alter the internal structure of the data.
     */
    @Override
    public void connect(int v1, int v2) {
        if (!isConnected(v1, v2)) {
            if (sizeOf(v1) <= sizeOf(v2)) {
                parent[find(v1)] = find(v2);
                parent[find(v2)] -= sizeOf(v1);      // Merge the size.
            } else {
                parent[find(v2)] = find(v1);
                parent[find(v1)] -= sizeOf(v2);
            }
        }
    }

    /** Returns the root of the set v belongs to.
     *  Path-compression is employed allowing for fast search-time. */
    private int find(int v) {
        validate(v);           // All methods above need find(), so we validate here.
        int root = v;
        while (parent(root) >= 0) {
            root = parent(root);
        }
        while (v != root) {
            int temp = parent(v);
            parent[v] = root;       // Change the parent of all nodes in the path to the root.
            v = temp;
        }
        return root;
    }

}
