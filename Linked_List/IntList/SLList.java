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

	public static void main(String[] args) {
		/* Create a list of one integer. */
		SLList L = new SLList(15);
		L.addFirst(10);
		L.addFirst(5);
		System.out.println(L.getFirst());
	}
}
