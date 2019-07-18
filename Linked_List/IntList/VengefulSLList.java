public class VengefulSLList<Item> extends SLList<Item> {
	private SLList<Item> deletedItems;

	/** a constructor of VengefullSLList. */
	public VengefulSLList() {
		super();                        // Must start with calling a super constructor.
		deletedItems = new SLList<>();
	}

	/** Prints out all the items deleted by removeLast(). */
	public void printLostItems() {
		deletedItems.print();
	}

	/** 
	 * Removes and returns the last item in the list.
	 * Put the deleted item in the deletedItems list.
	 */
	@Override
	public Item removeLast() {
		Item lastItem = super.removeLast();
		deletedItems.addLast(lastItem);
		return lastItem;
	}

	public static void main(String[] args) {
		VengefulSLList<Integer> vsl = new VengefulSLList<>();
		vsl.addFirst(3);
		vsl.addLast(4);
		vsl.addLast(5);
		vsl.addLast(10);
		vsl.print();
		vsl.removeLast();
		vsl.removeLast();
		vsl.printLostItems();
	}
}