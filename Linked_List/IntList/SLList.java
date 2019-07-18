/** An SLList is an integer list, which hides the terrible truth
  * of the nakeness within.
  */
public class SLList<Item> implements List61B<Item> {

	private class IntNode {
		public Item item;
		public IntNode next;
	    
	    // This looks like linked list. 
		public IntNode(Item i, IntNode n) {
			item = i;
			next = n;
		}
	}	
    
    /* This first item (if it exists), is at sentinel.next. */
	private IntNode sentinel;	
	private int size;   // cache the size of the list.
	private IntNode last;

	// Creates an empty SLList.
	public SLList() {
		sentinel = new IntNode(null, null);
		size = 0;
		last = sentinel;
	}

	/** Adds a new item to the front of this linked list. */
	public void addFirst(Item x) {
		size += 1;
		sentinel.next = new IntNode(x, sentinel.next);
	}

		/** Adds a new item to the last of this linked list. */
	public void addLast(Item x) {
	    size += 1;
	    last.next = new IntNode(x, null);
	    last = last.next;
	}

	/** Returns the first item in this linked list. */
	public Item getFirst() {
		if (size() == 0) {
			return null;
		}
		return sentinel.next.item;
	}

	/** Returns the last item in this linked list. */
	public Item getLast() {
		return last.item;
	}

	public Item removeLast() {
		IntNode p = sentinel;
		while (p.next != last) {
			p = p.next;
		}
		Item lastItem = last.item;
		p.next = null;
		last = p;
		size -= 1;
		return lastItem;
	}

	/** Returns the size of this linked list.
	public int size() {
		return size(sentinel.next);
	}
    */
	/** A helper method of size(), returns the size of a IntNode List.
	private static int size(IntNode L) {
		if (L.next == null) {
			return 1;
		}
		return 1 + size(L.next);
	}
	 */

	/** Returns the size of a list using caching. */
	public int size() {
		return size;
	}

	public Item get(int index) {
		if (index >= size()) {
			return null;
		}
		IntNode p = sentinel.next;
		return helpGet(index, p);
	}

	private Item helpGet(int index, IntNode p) {
		if (index == 0) {
			return p.item;
		}
		return helpGet(index - 1, p.next);
	}

	public static void main(String[] args) {
		/* Create a list of one integer. */
		SLList<Integer> L = new SLList<>();
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
