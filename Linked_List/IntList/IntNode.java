/**
*  Build our own list class. 
*/
public class IntNode {
	public int item;
	public IntNode next;
    
    // This looks like linked list. 
	public IntNode(int i, IntNode n) {
		item = i;
		next = n;
	}

    public void addFirst(int x) {
        IntNode tem = next;
        next = new IntNode(item, tem);
        item = x;
    }

    public static void main(String[] args) {
        IntNode L = new IntNode(5, null);
        L.addFirst(10);
        System.out.println(L.item);
    }
}