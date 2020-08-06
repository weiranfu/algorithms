package Tree.SegmentTree;

class RangeModule {

    class Node {
        int start;
        int end;
        boolean cover;
        Node left, right;
        Node(int s, int e) {
            start = s; end = e;
        }
    }

    Node root;

    public RangeModule() {
        root = new Node(0, (int)1e9);
    }

    public void addRange(int left, int right) {
        update(root, left, right - 1, true);
    }

    public boolean queryRange(int left, int right) {
        return query(root, left, right - 1);
    }

    public void removeRange(int left, int right) {
        update(root, left, right - 1, false);
    }

    private void pushUp(Node node) {
        node.cover = node.left.cover && node.right.cover;
    }

    private void pushDown(Node node) {
        int mid = node.start + (node.end - node.start) / 2;

        if (node.left == null) {                     // create new children
            node.left = new Node(node.start, mid);
            node.right = new Node(mid + 1, node.end);
        }
        if (node.cover) {                           // if cover, push down
            node.left.cover = true;
            node.right.cover = true;
        }
    }

    private void update(Node node, int L, int R, boolean b) {
        if (L <= node.start && node.end <= R) {
            node.cover = b;
            node.left = null;                           // remove children
            node.right = null;
            return;
        }

        if (b && node.cover) return;                 // stops smaller range add

        int mid = node.start + (node.end - node.start) / 2;

        pushDown(node);

        if (mid >= L) update(node.left, L, R, b);
        if (mid < R) update(node.right, L, R, b);

        pushUp(node);
    }

    private boolean query(Node node, int L, int R) {
        if (L <= node.start && node.end <= R) {
            return node.cover;
        }

        if (node.cover) return true;                    // stops smaller range query

        int mid = node.start + (node.end - node.start) / 2;
        if (node.left == null) {
            return node.cover;
        }
        boolean ans = true;
        if (mid >= L) ans = ans && query(node.left, L, R);
        if (mid < R) ans = ans && query(node.right, L, R);
        return ans;
    }
}