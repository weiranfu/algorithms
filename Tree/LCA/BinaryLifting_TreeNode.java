package Tree.LCA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryLifting_TreeNode {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    int N, max;
    Map<TreeNode, TreeNode> parent;
    Map<TreeNode, List<TreeNode>> up;
    Map<TreeNode, Integer> tin;
    Map<TreeNode, Integer> tout;
    int timer = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        parent = new HashMap<>();
        up = new HashMap<>();
        tin = new HashMap<>();
        tout = new HashMap<>();
        findParent(root, null);
        N = parent.size();
        max = (int)Math.ceil(Math.log10(N) / Math.log10(2));
        build(root);
        return lca(p, q);
    }

    private void findParent(TreeNode n, TreeNode p) {
        parent.put(n, p);
        if (n.left != null) findParent(n.left, n);
        if (n.right != null) findParent(n.right, n);
    }

    private void build(TreeNode n) {
        tin.put(n, timer++);
        List<TreeNode> list = new ArrayList<>();
        list.add(parent.get(n));
        for (int i = 1; i <= max; i++) {
            if (list.get(i - 1) == null) {
                list.add(null);
            } else {
                list.add(up.get(list.get(i - 1)).get(i - 1));
            }
        }
        up.put(n, list);
        if (n.left != null) build(n.left);
        if (n.right != null) build(n.right);
        tout.put(n, timer++);
    }

    private boolean isAncestor(TreeNode p, TreeNode q) {
        return tin.get(p) <= tin.get(q) && tout.get(p) >= tout.get(q);
    }

    private TreeNode lca(TreeNode p, TreeNode q) {
        if (isAncestor(p, q)) {
            return p;
        }
        if (isAncestor(q, p)) {
            return q;
        }
        for (int i = max; i >= 0; i--) {
            List<TreeNode> jump = up.get(p);
            TreeNode n = jump.get(i);
            if (n != null && !isAncestor(n, q)) {
                p = n;
            }
        }
        return up.get(p).get(0);
    }
}
