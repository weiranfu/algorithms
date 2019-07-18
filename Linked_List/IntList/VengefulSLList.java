public class VengefulSLList<Item> extends SLList<Item> {
	private SLList<Item> deletedItems;

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
}