package Tree.RedBlackTree;

public class RedBlackBST<Key extends Comparable<Key>> {
    private Key key;
    private String color;
    private RedBlackBST<Key> left;
    private RedBlackBST<Key> right;

    public RedBlackBST(Key key, String color) {
        this.key = key;
        this.color = color;
    }

    private RedBlackBST<Key> rotateRight(RedBlackBST<Key> T) {
        assert (T != null) && isRed(T.left);
        RedBlackBST<Key> temp = T.left;
        T.left = temp.right;
        temp.right = T;
        return temp;
    }

    private RedBlackBST<Key> rotateLeft(RedBlackBST<Key> T) {
        assert (T != null) && isRed(T.right);
        RedBlackBST<Key> temp = T.right;
        T.right = temp.left;
        temp.left = T;
        return temp;
    }

    public RedBlackBST<Key> put(RedBlackBST<Key> T, Key key) {
        if (T == null) {
            return new RedBlackBST<Key>(key, "RED");
        }
        if (key.compareTo(T.key) < 0) {
            T.left = put(T.left, key);
        } else if (key.compareTo(T.key) > 0) {
            T.right = put(T.right, key);
        } else {
            if (isRed(T.right) && !isRed(T.left)) {     // 1. First to check the right-leaning link.
                T = rotateLeft(T);
            }
            if (isRed(T.left) && isRed(T.left.left)) {  // 2. Second, check two consecutive left-leaning link.
                T = rotateRight(T);
            }
            if (isRed(T.left) && isRed(T.right)) {     // 3. Lastly, check left and right leaning link.
                flipColors(T);
            }
        }
        return T;
    }

    public boolean isRed(RedBlackBST<Key> t) {
        return t.color.equals("Red");
    }

    public void flipColors(RedBlackBST<Key> t) {
        t.color = "Red";
        t.left.color = "Black";
        t.right.color = "Black";
    }
}
