/** An SLList is an integer list, which hides the terrible truth
  * of the nakeness within.
  */
public class SLList {

	private static class IntNode {
		public int item;
		public IntNode next;
	    
	    // This looks like linked list. 
		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}
	}	

	private IntNode first;	
	private int size;   // cache the size of the list.

	// use InNode class to instantiate.
	public SLList(int x) {
		first = new IntNode(x, null);  // This is a pointer.
		size = 1;
	}

	/** Adds a new item to the front of this linked list. */
	public void addFirst(int x) {
		first = new IntNode(x, first);
		size += 1;
	}

		/** Adds a new item to the last of this linked list. */
	public void addLast(int x) {
		IntNode p = first;
		// move p until reaches the end of the list.
		while (p.next != null) {
			p = p.next;
		}
		p.next = new IntNode(x, null); 
		size += 1;
	}

	/** Returns the first item in this linked list. */
	public int getFirst() {
		return first.item;
	}

	/** Returns the size of this linked list. */
	public int size() {
		return size(first);
	}

	/** A helper method of size(), returns the size of a IntNode List. */
	private static int size(IntNode L) {
		if (L.next == null) {
			return 1;
		}
		return 1 + size(L.next);
	}

	/** Returns the size of a list using caching. */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		/* Create a list of one integer. */
		SLList L = new SLList(15);
		L.addFirst(10);
		L.addFirst(5);
		L.addLast(20);
		System.out.println(L.getFirst());
		System.out.println(L.size());
	}
}
