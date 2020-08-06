package Double_Ended_Queue;

/**
 * Deque means doubled-ended queue, which is a sequence containers with
 * dynamic size that can be expanded or contracted on both ends(front or back).
 * @param <T>
 * @author aranne
 */
public class LinkedListDeque<T> {
    /**
     * This is a generic linked list class.
     */
    private class StuffNode {
        private T item;
        private StuffNode next;
        private StuffNode prev;

        public StuffNode(StuffNode p, T i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    /**
     * Instantiates: creates an empty deque.
     */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds a generic type item to the front of this deque.
     */
    public void addFirst(T item) {
        StuffNode p = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    /**
     * Adds a generic type item to the back of this deque.
     */
    public void addLast(T item) {
        StuffNode p =  new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of this deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in this deque from first to last, separated by space.
     * If it's an empty deque, prints nothing.
     */
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the first item in this deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    /**
     * Removes and returns the last item in this deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last;
    }

    /**
     * Returns the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /**
     * Implements get() using recursion.
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursionHelper(sentinel.next, index);
    }

    /**
     * A helper method of getRecursion().
     */
    private T getRecursionHelper(StuffNode s, int index) {
        if (index == 0) {
            return s.item;
        }
        return getRecursionHelper(s.next, index - 1);
    }
}
