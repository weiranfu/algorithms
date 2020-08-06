package Tree.SegmentTree;

public class SegmentTree {

    class Node {
        int sum;
        int lazy;
    }

    int maxlen = 1000;
    Node[] tree = new Node[maxlen<<2];    // allocate space 4 * maxlen of array
    int[] array;
    int n;

    public SegmentTree(int[] array) {
        this.array = array;
        n = array.length;
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }
        build(1, n, 1);
    }

    public void build(int l, int r, int node) {
        if (l == r) {
            tree[node].sum = array[l];  
            return;
        }
        int mid = l + (r - l) / 2;

        build(l, mid, node * 2);
        build(mid + 1, r, node * 2 + 1);

        pushUp(node);                               // push up
    }

    private void pushUp(int node) {
        tree[node].sum = tree[node * 2].sum + tree[node * 2 + 1].sum;
    }

    private void pushDown(int node, int leftCnt, int rightCnt) {
        if (tree[node].lazy != 0) {
            tree[node * 2].lazy += tree[node].lazy;
            tree[node * 2 + 1].lazy += tree[node].lazy;
            tree[node * 2].sum += tree[node].lazy * leftCnt;
            tree[node * 2 + 1].sum += tree[node].lazy * rightCnt;
            tree[node].lazy = 0;
        }
    }

    public void update(int index, int delta) {
        updateHelper(index, delta, 1, n, 1);
    }

    /**
     * update a single point in array.
     * @param l left bound of node interval
     * @param r right bound of node interval
     * @param node node index
     */
    private void updateHelper(int index, int delta, int l, int r, int node) {
        if (l == r) {
            tree[node].sum += delta;
            return;
        }

        int mid = l + (r - l) / 2;
        if (mid >= index) {
            updateHelper(index, delta, l, mid, node * 2);
        } else {
            updateHelper(index, delta, mid + 1, r, node * 2 + 1);
        }

        pushUp(node);                                                           // push up
    }

    public void updateRange(int L, int R, int delta) {
        updateRangeHelper(L, R, delta, 1, n, 1);
    }

    /**
     * update a range of array
     * @param L left bound of updating interval
     * @param R right bound of updating interval
     * @param l left bound of node interval
     * @param r right bound of node interval
     * @param node node index
     */
    private void updateRangeHelper(int L, int R, int delta, int l, int r, int node) {
        if (L <= l && r <= R) {
            tree[node].sum += delta * (r - l + 1);
            tree[node].lazy += delta;
            return;
        }

        int mid = l + (r - l) / 2;
        pushDown(node, mid - l + 1, r - mid);                           // push down


        if (mid >= L) updateRangeHelper(L, R, delta, l, mid, node * 2);
        if (mid < R) updateRangeHelper(L, R, delta, mid + 1, r, node * 2 + 1);

        pushUp(node);                                                                  // push up
    }

    public int query(int L, int R) {
        return queryHelper(L, R, 1, n, 1);
    }

    /**
     * query the sum of a range of nums.
     * @param L left bound of query interval
     * @param R right bound of query interval
     * @param l left bound of node interval
     * @param r right bound of node interval
     * @param node node index
     */
    private int queryHelper(int L, int R, int l, int r, int node) {
        if (L <= l && r <= R) {
            return tree[node].sum;
        }

        int mid = l + (r - l) / 2;
        pushDown(node, mid - l + 1, r - mid);

        int ans = 0;
        if (mid >= L) ans += queryHelper(L, R, l, mid, node * 2);
        if (mid < R) ans += queryHelper(L, R, mid + 1, r, node * 2 + 1);
        return ans;
    }

    public void printTree() {
        int level = 1;
        for (int i = 0; i < tree.length; i += level, level *= 2) {
            for (int j = 0; i + j < tree.length && j < level; j++) {
                System.out.print(tree[i + j].sum + " ");
            }
            System.out.println();
        }
    }
}
