package Tree.LCA;

import java.util.*;

public class BruteForce {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * Recursion version
     * Try to find p and q in left and right subtrees.
     */
    public TreeNode lca1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        else if (root == p) return p;
        else if (root == q) return q;
        TreeNode left = lca1(root.left, p, q);
        TreeNode right = lca1(root.right, p, q);
        if (left != null && right != null) return root;
        else if (left != null) return left;
        return right;
    }

    /**
     * Iteration version
     * Use parent map to traverse back
     */
    public TreeNode lca2(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        parent.put(root, null);
        stack.push(root);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode curr = stack.pop();
            if (curr.left != null) {
                parent.put(curr.left, curr);
                stack.push(curr.left);
            }
            if (curr.right != null) {
                parent.put(curr.right, curr);
                stack.push(curr.right);
            }
        }

        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = parent.get(p);
        }
        while (q != null && !set.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}
