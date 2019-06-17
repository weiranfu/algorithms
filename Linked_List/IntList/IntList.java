/**
*  Build our own list class. 
*/
public class IntList {
	public int first;
	public IntList rest;
    
    // This looks like linked list. 
	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

    /** Return the size of a list. */
    public int size() {
    	if (this.rest == null) {
    		return 1;
    	}
    	return 1 + this.rest.size();
    }

    /** Get the ith item of the list. */
    public int get(int i) {
    	if (i == 0) {
    		return first;
    	} else {
    		return rest.get(i - 1);
    	}
    }

    public static void main(String[] args) {
    	IntList L = new IntList(15, null);
    	L = new IntList(10, L);
    	L = new IntList(5, L);
    	System.out.println(L.get(2));
    	System.out.println(L.size());
    }
}