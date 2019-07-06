/** Array based list. 
  * @author aranne
  */

/* Invariants:
 The position of the next item to be inserted is always size.
 size is always the number of items in the AList.
 The last item in the list is always in position size - 1.
*/

public class AList {
	private int[] items;
	private int size;
	private static final int RFactor = 2; // Resizing factor: every time we resize the array, we build a 2 times size of the array.

	/** Creates an empty list. */
	public AList() {
		items = new int[100];
		size = 0;
	}

    /** Resize the underlying array to the target capacity. */
    private void resize(int capacity) {
		int[] a = new int[capacity];
		System.arraycopy(items, 0, a, 0, size);
		items = a;
	}

    /** Inserts X into the back of this list. */
    public void addLast(int x) {
    	if (size == items.length) {
    		resize(size * RFactor);
    	}
    	items[size] = x;
    	size += 1;  
    }

    /** Returns the item from the back of this list. */
    public int getLast() {
    	return items[size - 1];
    }

    /** Returns the ith item in this list. */
    public int get(int i) {
    	if (i < size) {
    		return items[i];
    	}
    	System.out.println("The list only has " + (size - 1) + " items.");
    	return 0;
    }

    /** Returns the number of items in this list. */
    public int size() {
    	return size;
    }

    /** Deletes the item from the back of this list and 
      * returns deleted item. */
    public int removeLast() {
    	if ((double)size / items.length <= 0.25) {
    		resize(items.length / 2);
		}
    	int lastItem = getLast();
    	size -= 1;
    	return lastItem;
    }
}