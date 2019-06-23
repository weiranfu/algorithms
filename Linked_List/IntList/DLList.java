/** An DLList is an integer list, 
  * which has each node tracking double-direction nodes in the linked list.
  */
public class DLList {

	private static class IntNode {
		public int item;
		public IntNode next;
		public IntNode prev;
	    
	    // This looks like linked list. 
		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}
	}	
    
    /* This first item (if it exists), is at sentinel.next. */
	private IntNode sentinel;	
	private int size;   // cache the size of the list.

	// Creates an empty DLList.
	public DLList() {
		sentinel = new IntNode(816, null);
		size = 0;
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}
	// use InNode class to instantiate.
	public DLList(int x) {
		sentinel = new IntNode(816, null);
		sentinel.next = new IntNode(x, sentinel);
		sentinel.prev = sentinel.next;
		sentinel.next.prev = sentinel;
		size = 1;
	}

	/** Adds a new item to the front of this linked list. */
	public void addFirst(int x) {
		size += 1;
		IntNode p = sentinel.next;
		sentinel.next = new IntNode(x, p);
		p.prev = sentinel.next;
		sentinel.next.prev = sentinel;
	}

		/** Adds a new item to the last of this linked list. */
	public void addLast(int x) {
	    size += 1;
	    IntNode p = sentinel.prev;
	    sentinel.prev = new IntNode(x, sentinel);
	    sentinel.prev.prev = p;
	    p.next = sentinel.prev;
	}

	/** Returns the first item in this linked list. */
	public int getFirst() {
		return sentinel.next.item;
	}

	/** Returns the last item in this linked list. */
	public int getLast() {
		return sentinel.prev.item;
	}

	public void removeLast() {
		IntNode p = sentinel.prev.prev;
		sentinel.prev = p;
		p.next = sentinel;
	}

	/** Returns the size of this linked list. */
	public int size() {
		return size(sentinel.next);
	}

	/** A helper method of size(), returns the size of a IntNode List. */
	private static int size(IntNode L) {
		if (L.next.item == 816) {
			return 1;
		}
		return 1 + size(L.next);
	}

	/** Returns the size of a list using caching. 
	public int size() {
		return size;
	}
    */
	public static void main(String[] args) {
		/* Create a list of one integer. */
		DLList L = new DLList();
		System.out.println(L.getFirst());
		L.addLast(20);
		L.addFirst(10);
		L.addFirst(5);
		System.out.println(L.getFirst());
		System.out.println(L.size());
	    L.removeLast();
		System.out.println(L.getLast());

	}
}
