package Tree.LCA;

/**
 * Find the Lowest Common Ancestor(LCA) between two nodes v, w.
 * For each node we will precompute its ancestor above him, its ancestor two nodes above, its ancestor four above
 * up[i][j] is the 2^j-th ancestor above the node i with i=1...N, j=0...ceil(log(N))
 * Suppose now we received a query (u, v)
 * we climb the ancestors of u until we find the highest (i.e. closest to the root) node, which is not an ancestor of v
 * Preprocessing takes O(NlogN), and each LCA query takes O(logN).
 */
public class BinaryLifting {
    int[] nodes;        // tree nodes
    int[][] child;      // children of each tree nodes.
    int n;
    int timer;
    int l;
    int[] tin;
    int[] tout;
    int[][] up;

    public BinaryLifting(int[] nodes, int[][] child) {
        this.nodes = nodes;
        this.child = child;
        n = nodes.length;
        l = (int)Math.ceil(Math.log10(n) / Math.log10(2));   // log2(n)
        tin = new int[n];
        tout = new int[n];
        up = new int[n][l + 1];

        build(0, -1);             // root's parent is -1
    }

    private void build(int v, int p) {
        tin[v] = ++timer;

        up[v][0] = p;
        for (int i = 1; i <= l; i++) {
            if (up[v][i - 1] == -1) {    // jump to root;
                up[v][i] = -1;
            } else {
                up[v][i] = up[up[v][i - 1]][i - 1];
            }
        }

        for (int w : child[v]) {
            build(w, v);
        }

        tout[v] = ++timer;
    }

    /**
     * Is node v ancestor of node w
     */
    public boolean isAncestor(int v, int w) {
        return tin[v] <= tin[w] && tout[v] >= tout[w];
    }

    /**
     * Find the lowest common ancestor between node v and w
     */
    public int lca(int v, int w) {
        if (isAncestor(v, w)) {
            return v;
        }
        if (isAncestor(w, v)) {
            return w;
        }
        for (int i = l; i >= 0; i--) {
            if (!isAncestor(up[v][i], w)) {
                v = up[v][i];             // keep update v
            }
        }
        return up[v][0];
    }
}
