package Set.DisjointSets;

public class BasicUnionFind {

    int[] uf;

    public BasicUnionFind(int n) {
        uf = new int[n];
        for (int i = 0; i < n; i++) {
            uf[i] = i;
        }
    }

    /**
     * O(logn) 复杂度
     */
    private int find(int i) {
        if (i != uf[i]) {
            uf[i] = find(uf[i]);
        }
        return uf[i];
    }

    public void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);
        if (p1 != p2) {
            uf[p1] = p2;
        }
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}
