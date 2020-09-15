package Tree.SegmentTree;

/**
 * Lazy Propagation is for range updates
 * mark parent node for future query, don't update every nodes in the range.
 */
public class SegmentTree2 {
    class Node {
        int start;
        int end;
        int sum;   /* can be max or min */
        int lazy;  /* for lazy propagation */
        Node left;
        Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    Node root;   /* root node of segment tree */

    public SegmentTree2(int[] array) {
        root = new Node(0, array.length - 1);
        build(root, array);
    }

    private void build(Node node, int[] array) {
        if (node.start == node.end) {
            node.sum = array[node.start];
            return;
        }
        int mid = node.start + (node.end - node.start) / 2;
        node.left = new Node(node.start, mid);
        node.right = new Node(mid + 1, node.end);            // don't include mid

        build(node.left, array);
        build(node.right, array);

        pushUp(node);                                        // push up
    }

    /* push up sum */
    private void pushUp(Node node) {
        node.sum = node.left.sum + node.right.sum;
    }

    /* push down lazy tag */
    private void pushDown(Node node) {
        if (node.lazy != 0) {
            node.left.lazy += node.lazy;
            node.right.lazy += node.lazy;
            node.left.sum += node.lazy * (node.left.end - node.left.start + 1);
            node.right.sum += node.lazy * (node.right.end - node.left.start + 1);
            node.lazy = 0;
        }
    }

    public void update(int index, int delta) {
        updateHelper(root, index, delta);
    }

    public void updateHelper(Node node, int index, int delta) {
        if (node.start == node.end) {                           // leaf node
            node.sum += delta;
            return;
        }
        int mid = node.start + (node.end - node.start) / 2;
        if (index <= mid) {
            updateHelper(node.left, index, delta);
        } else {
            updateHelper(node.right, index, delta);
        }
        pushUp(node);                                           // push up
    }

    /**
     * update all nodes in range (L, R) with an increment delta.
     * @param delta an increment added to all nodes in range(L, R)
     */
    public void updateRange(int L, int R, int delta) {
        updateRangeHelper(root, L, R, delta);
    }


    public void updateRangeHelper(Node node, int L, int R, int delta) {
        if (L <= node.start && node.end <= R) {                 // lazy propagation
            node.sum += delta * (node.end - node.start + 1);    // total sum of children
            node.lazy += delta;                                 // mark lazy propagation
            return;
        }

        pushDown(node);                                         // push down

        int mid = node.start + (node.end - node.start) / 2;
        if (mid >= L) updateRangeHelper(node.left, L, R, delta);
        if (mid < R) updateRangeHelper(node.right, L, R, delta);

        pushUp(node);                                           // push up
    }

    public int query(int L, int R) {
        return queryHelper(root, L, R);
    }

    public int queryHelper(Node node, int L, int R) {
        if (L <= node.start && node.end <= R) {
            return node.sum;
        }

        pushDown(node);                                        // push down

        int mid = node.start + (node.end - node.start) / 2;
        int ans = 0;
        if (mid >= L) ans += queryHelper(node.left, L, R);
        if (mid < R) ans += queryHelper(node.right, L, R);
        return ans;
    }
}
