package Tree.BST;

public class BST<Key> {
	private Key key;
	private BST<Key> left;
	private BST<Key> right;

	public BST(Key key, BST<Key> left, BST<Key> right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public BST(Key key) {
		this.key = key;      // In this case, this.left and this.right will be null.
	}

	/** Returns the BST rooted at the node whose key matched the key parameter. */
	public static <Key extends Comparable<Key>> BST<Key> find(BST<Key> T, Key key) {
		if (T == null) {      // Find until the leaves if the key doesn't exist.
			return null;
		}
		if (key.equals(T.key)) {
			return T;
		} else if (key.compareTo(T.key) < 0) { // Key extends Comparable<Key>
			return find(T.left, key);
		} else {
			return find(T.right, key);
		}
	}

	/** Return the new BST after we insert a new node. (Non-destructively)
	 * If we find it, we do nothing.(Just return the old BST.)
	 * If not, we add a new node to either left or right of the leaf.
	 */
	public static <Key extends Comparable<Key>> BST<Key> insert(BST<Key> T, Key key) {
		if (T == null) {
			return new BST<Key>(key);
		}
		if (key.compareTo(T.key) < 0) {
			T.left = insert(T.left, key);
		} else if (key.compareTo(T.key) > 0){
			T.right = insert(T.right, key);
		}
		return T;       // When all recursions are done , return the new BST.
	}                   // OR when we find key.equals(T.key), return the old BST.

	/** Delete a node in BST which has
	 *  case 1: no child, it is the leaf, just delete its parent pointer.
	 *  case 2: 1 child, reassign the parent's child pointer to the node's child.
	 *  case 3: 2 children, choose the right-most node in the left subtree or
	 *          the left-most node in the right subtree to replace the deleted node.
	 *  This is called hibbard deletion.
	 */
	public static <Key extends Comparable<Key>> BST<Key> delete(BST<Key> T, Key key) {
		if (T == null) {
			return null;
		}
		if (key.compareTo(T.key) < 0) {
			T.left = delete(T.left, key);
		} else if (key.compareTo(T.key) > 0) {
			T.right = delete(T.right, key);
		} else {
			if (T.left == null && T.right == null) {  // Case 1: no child.
				return null;
			} else if (T.left == null) {  // Case 2: 1 child.
				return T.right;
			} else if (T.right == null) { // Case 2: 1 child.
				return T.left;
			} else {                      // Case 3: 2 children.
				BST<Key> temp = T.right;
				while (temp.left != null) {  // Find the left-most node in right subtree.
					temp = temp.left;
				}
				T.key = temp.key;
				T.right = delete(T.right, temp.key);  // Delete temp node.
			}
		}
		return T;    // When all recursions are done, return the new BST.
	}

	public <Key extends Comparable<Key>> Key findKthSmallest(BST<Key> T, int k) {
		int[] cnt = new int[1];
		return findKthSmallestHelper(T, k, cnt);
	}

	public <Key extends Comparable<Key>> Key findKthSmallestHelper(BST<Key> T, int k, int[] cnt) {
		if (T == null) return null;

		Key left = findKthSmallestHelper(T.left, k, cnt);

		if (left != null) {
			return left;
		}

		cnt[0]++;
		if (cnt[0] == k) {
			return T.key;
		}

		return findKthSmallestHelper(T.right, k, cnt);
	}
}