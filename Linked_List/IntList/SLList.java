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

	// use InNode class to instantiate.
	public SLList(int x) {
		first = new IntNode(x, null);  // This is a pointer.
	}

	/** Adds a new item to the front of this linked list. */
	public void addFirst(int x) {
		first = new IntNode(x, first);
	}

	/** Returns the first item in this linked list. */
	public int getFirst() {
		return first.item;
	}

	/** Adds a new item to the last of this linked list. */
	public void addLast(int x) {
		IntNode ptn = first;
		while (ptn.next != null) {
			ptn = ptn.next;
		}
		ptn.next = new IntNode(x, null); 
	}

	/** Returns the size of this linked list. */
	public int size() {
		return size(first);
	}

	/** A helper method of size(), returns the size of a IntNode List. */
	public static int size(IntNode L) {
		if (L.next == null) {
			return 1;
		}
		return 1 + size(L.next);
	}

	public static void main(String[] args) {
		/* Create a list of one integer. */
		SLList L = new SLList(15);
		L.addFirst(10);
		L.addFirst(5);
		System.out.println(L.getFirst());
		System.out.println(L.size());
	}
}
