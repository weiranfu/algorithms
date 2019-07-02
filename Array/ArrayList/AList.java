/** Array based list. 
  * @author aranne
  */
public class AList {
	private int[] items = new int[100];
	private int size;

	/** Creates an empty list. */
	public AList() {
		size = 0;
	}

    public AList(int x) {
    	items[0] = x;
    	size = 1;
    }

    /** Inserts X into the back of this list. */
    public void addLast(int x) {
    	if (size == items.length) {
    		int[] a = new int[size + 1];
    	    System.arraycopy(items, 0, a, 0, size);
    	    items = a;
    	}
    	items[size] = x;
    	size += 1;  
    }

    /** Returns the item from the back of this list. */
    public int getLast() {
    	int p = size;
    	p -= 1;
    	return items[p];
    }

    /** Returns the ith item in this list. */
    public int get(int i) {
    	if (i < size) {
    		return items[i];
    	}
    	System.out.println("The list only has " + (size - 1) + " items.");
    	return null;
    }

    /** Returns the number of items in this list. */
    public int size() {
    	return size;
    }

    /** Deletes the item from the back of this list and 
      * returns deleted item. */
    public int removeLast() {
    	size -= 1;
    	return items[size];
    }
}