package Tree.Cartesian;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Maximum Binary Tree
 *
 * Given an integer array with no duplicates,
 * 1. The root is the maximum number in the array.
 * 2. The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * 3. The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 *
 * [3, 2, 6, 0, 5]
 *        6
 *    3       5
 *      2   0
 */
public class Cartesian {
    class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    int[] nums;
    Node root;

    public Cartesian(int[] nums) {
        this.nums = nums;
        Deque<Node> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            Node curr = new Node(nums[i]);
            while (!deque.isEmpty() && deque.peekLast().val < curr.val) {
                curr.left = deque.pollLast();
            }
            if (!deque.isEmpty()) {
                deque.peekLast().right = curr; // append to the right
            }
            deque.add(curr);
        }
        root = deque.peekFirst();
    }

    /**
     * When we insert a new val into nums[], we append it to the last position of nums[].
     * @return the new Tree with new nums[].
     */
    public Node insert(int val) {
        return insertHelper(root, val);
    }
    private Node insertHelper(Node root, int val) {
        if (root == null) return new Node(val);
        if (root.val < val) {
            Node newRoot = new Node(val);
            newRoot.left = root;
            return newRoot;
        }
        root.right = insertHelper(root.right, val); // can only insert right part of tree
        return root;
    }
}
