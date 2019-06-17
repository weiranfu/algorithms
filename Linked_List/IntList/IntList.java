/**
*  Build our own list class. 
*/
public class IntList {
	public int first;
	public IntList rest;
    
    // This looks like linked list. 
	public IntList(Int f, IntList r) {
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
    		return this.first;
    	} else {
    		// this can't be reassigned. copy this to p.
    		IntList p = this;
    		p = p.rest;
    		return get(i - 1);
    	}
    }
}