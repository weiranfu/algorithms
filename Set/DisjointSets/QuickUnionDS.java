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
            parent[i] = i;
        }
    }

    /** Returns the parent of an item in a set. */
    private int find(int x) {
        if (parent[x] < 0) {
            return x;
        }
        return find(parent[x]);
    }

    /** Connect two disjoint sets together. */
    @Override
    public void connect(int x, int y) {
        int xp = find(x);
        int yp = find(y);
        if (parent[xp] != parent[yp]) {
            parent[xp] = yp;      // Connect two roots.
        }
    }

    /** Returns true if two sets are connected. */
    @Override
    public boolean isConnected(int x, int y) {
        return parent[x] == parent[y];
    }
}
