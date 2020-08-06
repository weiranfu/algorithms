package Tree.BST;

public class BST_Kth {
    class Node {
        int val;
        int sum;
        Node left;
        Node right;
        public Node(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }
    }
    Node rootNode;

    public Node add(Node n, int value) {
        if (n == null) {
            return new Node(value, 1);
        }
        if (n.val < value) {
            n.right = add(n.right, value);
        } else {
            n.left = add(n.left, value);
        }
        int left = (n.left == null) ? 0 : n.left.sum;
        int right = (n.right == null) ? 0 : n.right.sum;
        n.sum = left + right + 1;
        return n;
    }

    public int findKthSmallest(Node n, int k) {
        if (n == null || n.sum < k) return -1;

        int left = (n.left != null) ? n.left.sum : 0;
        if (left >= k) {
            return findKthSmallest(n.left, k);
        }
        if (left == k - 1) {
            return n.val;
        }
        return findKthSmallest(n.right, k - 1 - left);
    }
}
