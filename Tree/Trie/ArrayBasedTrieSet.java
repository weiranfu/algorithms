package Tree.Trie;

import java.util.List;
import java.util.ArrayList;

/** DataIndexedCharMap is used to store the map from character to Node.
 * In the real java implementation, trie uses HashMap instead of DataIndexedCharMap.
 */
public class ArrayBasedTrieSet {
    private static final int R = 128;  // ASCII
    private Node root; // root of the Trie;
    // Characters will be stored in the index of its parent's DataIndexedCharMap next.
    private class Node {
        private boolean isEnd;
        private DataIndexedCharMap<Node> next; // Store the children of this Node.
        public Node(boolean b, int R) {
            isEnd = b;
            next = new DataIndexedCharMap<>(R);
        }
    }

    /** Returns all of the keys in a Trie. */
    public ArrayList<String> collect() {
        ArrayList<String> als = new ArrayList<>();
        for (char c : root.next.keys()) {
            collectHelper(c + "", als, root.next.get(c));
        }
        return als;
    }
    private void collectHelper(String s, List<String> x, Node n) {
        if (n.isEnd) {
            x.add(s);
        }
        for (char c : n.next.keys()) {
            collectHelper(s + c, x, n.next.get(c));
        }
    }

    /** Returns all keys that contains the prefix passed in. */
    public ArrayList<String> keysWithPrefix(String s) {
        ArrayList<String> als = new ArrayList<>();
        Node n = root;
        for (int i = 0; i < s.length(); i += 1) {
            if (n.next.keys().contains(s.charAt(i))) {
                n = n.next.get(s.charAt(i));
            } else {
                return null;
            }
        }
        collectHelper(s, als, n);
        return als;
    }

    /** Return the longest prefix of the given string in the trie. */
    public String longestPrefixOf(String s) {
        Node n = root;
        for (int i = 0; i < s.length(); i += 1) {
            if (n.next.keys().contains(s.charAt(i))) {
                n = n.next.get(s.charAt(i));
            } else {
                return s.substring(0, i);
            }
        }
        return s;
    }
}
