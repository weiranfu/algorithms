package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert a tree to a graph for dfs
 */
public class TreeToGraph {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val, TreeNode l, TreeNode r) {
            this.val = val; left = l; right = r;
        }
    }

    List<List<Integer>> g;
    int id;

    public TreeToGraph(TreeNode root) {
        g = new ArrayList<>();
        id = 0;
        dfs(root, -1);
    }

    private void dfs(TreeNode n, int pa) {
        if (n == null) return;
        int now = id++;
        g.add(new ArrayList<>());
        if (pa != -1) {
            g.get(pa).add(now);
            g.get(now).add(pa);
        }
        dfs(n.left, now);
        dfs(n.right, now);
    }
}
