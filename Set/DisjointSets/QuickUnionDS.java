package DisjointSets;

/**
 * connect() is O(n), isConnect() is O(n).
 * However the best case of connect() is O(1), so it's Quick Union.
 */
public class QuickUnionDS implements DisjointSet {
    private int[] parent;

    public QuickUnionDS(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i += 1) {
            parent[i] = -1;     // Each value is -1, because they're all roots.
        }
    }

    /** Returns the root of an item in a set. */
    private int find(int x) {
        while (parent[x] >= 0) {
            x = parent[x];
        }
        return x;
    }

    /** Connect two disjoint sets together. */
    @Override
    public void connect(int x, int y) {
        int xp = find(x);
        int yp = find(y);
        parent[xp] = yp;      // Connect two roots.
    }

    /** Returns true if two sets are connected. */
    @Override
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
