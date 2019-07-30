package DisjointSets;
/** connect() is O(n), isConnected() is O(1). */
public class QuickFindDS implements DisjointSet {
    private int[] id;

    public QuickFindDS(int N) {
        id = new int[N];
        for (int i = 0; i < N; i += 1) {  // Initialize each one-element set an id.
            id[i] = i;
        }
    }

    /** Connect two disjoint sets together. */
    public void connect(int x, int y) {
        int xid = id[x];
        int yid = id[y];
        if (xid != yid) {
            for (int i = 0; i < id.length; i += 1) {
                if (id[i] == xid) {
                    id[i] = yid;
                }
            }
        }
    }

    /** Returns true if two items are connected. */
    public boolean isConnected(int x, int y) {
        return id[x] == id[y];
    }
}
