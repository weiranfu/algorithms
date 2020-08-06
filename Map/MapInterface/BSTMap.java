package Map.MapInterface;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Aranne
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private Set<K> keySet;

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
        keySet = new HashSet<>();
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls get() with a null key.");
        }
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            keySet.add(key);
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Calls put() with a null key.");
        }
        if (value == null) {
            remove(key);
            return;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /** A result-pair of Node and returnValue. */
    private class Result {
        private Node node;
        private V value;

        public Result(Node p, V v) {
            this.node = p;
            this.value = v;
        }
    }

    /** Removes KEY from the tree rooted in p. */
    private Result removeHelper(K key, Node p) {
        if (p == null) {
            return new Result(null, null);
        }
        V returnValue;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            Result r1 = removeHelper(key, p.left);
            p.left = r1.node;
            returnValue = r1.value;
        } else if (cmp > 0) {
            Result r2 = removeHelper(key, p.right);
            p.right = r2.node;
            returnValue = r2.value;
        } else {
            returnValue = p.value;
            size -= 1;
            keySet.remove(key);
            if (p.left == null && p.right == null) {
                return new Result(null, returnValue);
            } else if (p.left == null) {
                return new Result(p.right, returnValue);
            } else if (p.right == null) {
                return new Result(p.left, returnValue);
            } else {
                Node temp = p.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                p.key = temp.key;
                p.value = temp.value;
                p.right = removeHelper(temp.key, p.right).node;
                // Because we use remove() method once more, size--, so we need size++.
                size += 1;
                // Because we use remove() method once more, keySet.remove(), so we need keySet.add()
                keySet.add(p.key);
            }
        }
        return new Result(p, returnValue);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls remove() with a null key.");
        }
        Result r = removeHelper(key, root);
        root = r.node;
        return r.value;

    }

    /** Removes KEY from the tree rooted in p, only if it is
     * currently mapping to the VALUE. */
    private Result removePairHelper(K key, V value, Node p) {
        if (p == null) {
            return new Result(null, null);
        }
        V returnValue;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            Result r1 = removePairHelper(key, value, p.left);
            p.left = r1.node;
            returnValue = r1.value;
        } else if (cmp > 0) {
            Result r2 = removePairHelper(key, value, p.right);
            p.right = r2.node;
            returnValue = r2.value;
        } else {
            if (p.value.equals(value)) {
                returnValue = p.value;
                size -= 1;
                keySet.remove(key);
                if (p.left == null && p.right == null) {
                    return new Result(null, returnValue);
                } else if (p.left == null) {
                    return new Result(p.right, returnValue);
                } else if (p.right == null) {
                    return new Result(p.left, returnValue);
                } else {
                    Node temp = p.right;
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    p.key = temp.key;
                    p.value = temp.value;
                    // Using removeHelper() instead of removePairHelper()
                    p.right = removeHelper(p.key, p.right).node;
                    // Because we use remove() method once more, size--, so we need size++.
                    size += 1;
                    // Because we use remove() once more, keySet.remove(), so we need keySet.add()
                    keySet.add(p.key);
                }
            } else {
                returnValue = null;
            }
        }
        return new Result(p, returnValue);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Calls on remove() with null key.");
        }
        Result r = removePairHelper(key, value, root);
        root = r.node;
        return r.value;
    }

    /** Enable to iterate on all the keys in this BSTMap. */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    /** Prints the BSTMap in order from smaller to larger Key. */
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node p) {
        if (p.left == null & p.right == null) {
            printNode(p);
        } else if (p.left == null) {
            printNode(p);
            printInOrder(p.right);
        } else if (p.right == null) {
            printInOrder(p.left);
            printNode(p);
        } else {
            printInOrder(p.left);
            printNode(p);
            printInOrder(p.right);
        }
    }

    /** Prints this node with its Key and Value. */
    private void printNode(Node p) {
        System.out.print(p.key + " ");
        System.out.println(p.value);
    }
}
