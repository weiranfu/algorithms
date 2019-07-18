public class RotatingSLList<Item> extends SLList<Item> {
	/** 
	 * Rotates every element one spot to the right,
	 * moving the last item to the front of the list.
	 * e.g. Calling rotateRight() on [5, 9, 15, 22] should return [22, 5, 9, 15].
	 */
	public void rotateRight() {
		Item last = removeLast();
		addFirst(last);
	}

	public static void main(String[] args) {
		RotatingSLList<Integer> rsl = new RotatingSLList<>();
		rsl.addFirst(1);
		rsl.addLast(2);
		rsl.addLast(3);
		rsl.addLast(4);
		rsl.print();
		rsl.rotateRight();
		rsl.print();
	}
}