public interface List61B<Item> {
	/** Adds a new item to the front of this list. */
	void addFirst(Item x);

		/** Adds a new item to the last of this list. */
	void addLast(Item x);

	/** Returns the first item in this list. */
	Item getFirst();

	/** Returns the last item in this list. */
	Item getLast();

    /** Remove the last item in this list. */
	Item removeLast();

	/** Returns the size of this list. */
	int size();

	/** Return the item at given index. */
	Item get(int index);

	/** Prints out the entire list. */
	default void print() {
		for (int i = 0; i < size(); i += 1) {
			System.out.println(get(i) + " ");
		}
		System.out.println();
	}
}