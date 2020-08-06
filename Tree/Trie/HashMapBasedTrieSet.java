package Tree.Trie;

import java.util.HashMap;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
/** HashMap based trie will save much space comparing to Array based trie. */
public class HashMapBasedTrieSet {
    private class Node {
        private boolean isEnd;
        private HashMap<Character, Node> children;
        public Node() {
            children = new HashMap<>();
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }
    Node root;
    public HashMapBasedTrieSet() {
        root = new Node();
    }

    /** Insert a word into a trie. */
    public void insert(String s) {
        Node n = root;
        for (int i = 0; i < s.length(); i += 1) {
            if (!n.children.containsKey(s.charAt(i))) {
                n.children.put(s.charAt(i), new Node());
            }
            n = n.children.get(s.charAt(i));
        }
        n.setEnd();
    }

    /** Search for a key in a trie. */
    public boolean search(String s) {
        Node n = root;
        for (int i = 0; i < s.length(); i += 1) {
            if (!n.children.containsKey(s.charAt(i))) {
                return false;
            }
            n = n.children.get(s.charAt(i));
        }
        return n.isEnd();
    }

    /** Returns all the keys in a trie. */
    public ArrayList<String> collect() {
        Node n = root;
        ArrayList<String> collection = new ArrayList<>();
        for (char c : n.children.keySet()) {
            collectHelper(collection, c + "", n.children.get(c));
        }
        return collection;
    }
    private void collectHelper(ArrayList<String> collection, String s, Node n) {
        if (n.isEnd()) {
            collection.add(s);
        }
        for (char c : n.children.keySet()) {
            collectHelper(collection, s + c, n.children.get(c));
        }
    }

    /** Returns all keys that contains the prefix passed in. */
    public ArrayList<String> keysWithPrefix(String prefix) {
        Node n = root;
        ArrayList<String> keys = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i += 1) {
            if (!n.children.containsKey(prefix.charAt(i))) {
                return keys;
            }
            n = n.children.get(prefix.charAt(i));
        }
        collectHelper(keys, prefix, n);
        return keys;
    }

    /** Returns the longest prefix of String s in the trie. */
    public String longestPrefixOf(String s) {
        Node n = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i += 1) {
            if (!n.children.containsKey(s.charAt(i))) {
                break;
            }
            sb.append(s.charAt(i));
            n = n.children.get(s.charAt(i));
        }
        return sb.toString();
    }

    @Test
    public void test() {
        HashMapBasedTrieSet t = new HashMapBasedTrieSet();
        assertEquals(t.collect(), new ArrayList<String>());
        t.insert("horse");
        t.insert("macbook");
        assertFalse(t.search("apple"));
        assertFalse(t.search("horst"));
        assertTrue(t.search("horse"));
        t.insert("macbookpro");
        assertTrue(t.search("macbook"));
        assertFalse(t.search("mac"));
        System.out.println(t.collect());
        System.out.println("Test keyWithPrefix() ->");
        System.out.println(t.keysWithPrefix("oppo"));
        System.out.println(t.keysWithPrefix("hor"));
        t.insert("macbookmini");
        t.insert("macbooknew");
        System.out.println(t.keysWithPrefix("macbook"));
        System.out.println("Test longestPrefixOf() ->");
        System.out.println(t.longestPrefixOf("ttt"));
        System.out.println(t.longestPrefixOf("macos"));
        System.out.println(t.longestPrefixOf("horse"));
    }
}
