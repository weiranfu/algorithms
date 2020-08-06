package Graph.DFS;

import java.util.ArrayList;
import java.util.List;

public class N_aryLevelOrderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, root, res);
        return res;
    }
    private void dfs(int depth, Node n, List<List<Integer>> res) {
        if (n == null) return;
        if (depth == res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(depth).add(n.val);
        for (Node next : n.children) {
            dfs(depth + 1, next, res);
        }
    }
}
