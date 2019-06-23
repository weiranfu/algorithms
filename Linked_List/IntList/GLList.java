/** An GLList is a generic list, 
  * which can store data of arbitary data type.
  */
public class GLList<LochNess> {

	private class StuffNode {
		public LochNess item;
		public StuffNode next;
		public StuffNode prev;
	    
	    // This looks like linked list. 
		public StuffNode(LochNess i, StuffNode n) {
			item = i;
			next = n;
		}
	}	
    
    /* This first item (if it exists), is at sentinel.next. */
	private StuffNode sentinel;	
	private int size;   // cache the size of the list.

	// Creates an empty GLList.
	public GLList() {
		sentinel = new StuffNode(null, null); // null can be any reference data type.
		size = 0;
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}
	// use InNode class to instantiate.
	public GLList(LochNess x) {
		sentinel = new StuffNode(null, null);
		sentinel.next = new StuffNode(x, sentinel);
		sentinel.prev = sentinel.next;
		sentinel.next.prev = sentinel;
		size = 1;
	}

	/** Adds a new item to the front of this linked list. */
	public void addFirst(LochNess x) {
		size += 1;
		StuffNode p = sentinel.next;
		sentinel.next = new StuffNode(x, p);
		p.prev = sentinel.next;
		sentinel.next.prev = sentinel;
	}

		/** Adds a new item to the last of this linked list. */
	public void addLast(LochNess x) {
	    size += 1;
	    StuffNode p = sentinel.prev;
	    sentinel.prev = new StuffNode(x, sentinel);
	    sentinel.prev.prev = p;
	    p.next = sentinel.prev;
	}

	/** Returns the first item in this linked list. */
	public LochNess getFirst() {
		return sentinel.next.item;
	}

	/** Returns the last item in this linked list. */
	public LochNess getLast() {
		return sentinel.prev.item;
	}

	public void removeLast() {
		StuffNode p = sentinel.prev.prev;
		sentinel.prev = p;
		p.next = sentinel;
	}

	/** Returns the size of this linked list. */
	public int size() {
		return size(sentinel.next);
	}

	/** A helper method of size(), returns the size of a StuffNode List. */
	private int size(StuffNode L) {
		if (L.next.item == null) {
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
		GLList<Integer> L = new GLList<>();
		System.out.println(L.getFirst());
		L.addLast(20);
		L.addFirst(10);
		L.addFirst(5);
		System.out.println(L.getFirst());
		System.out.println(L.size());
	    L.removeLast();
		System.out.println(L.getLast());

		GLList<String> L2 = new GLList<>("bears");
		L2.addLast("bones");
		L2.addFirst("cakes");
		L2.addFirst("bananas");
		System.out.println(L2.getFirst());
		System.out.println(L2.size());
		L2.removeLast();
		System.out.println(L2.getLast());

	}
}
