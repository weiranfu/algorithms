package Deque_Interface;

/**
 * Deque means doubled-ended queue, which is a sequence containers with
 * dynamic size that can be expanded or contracted on both ends(front or back).
 * @param <Item>
 * @author aranne
 */
public class LinkedListDeque<Item> implements Deque<Item> {
    /**
     * This is a generic linked list class.
     */
    private class StuffNode {
        private Item item;
        private StuffNode next;
        private StuffNode prev;

        private StuffNode(StuffNode p, Item i, StuffNode n) {
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

    /** Adds a generic type item to the front of this deque. */
    @Override
    public void addFirst(Item item) {
        StuffNode p = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    /** Adds a generic type item to the back of this deque. */
    @Override
    public void addLast(Item item) {
        StuffNode p =  new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the size of this deque. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in this deque from first to last, separated by space.
     * If it's an empty deque, prints nothing.
     */
    @Override
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
    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    /**
     * Removes and returns the last item in this deque.
     * If no such item exists, returns null.
     */
    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last;
    }

    /**
     * Returns the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    @Override
    public Item get(int index) {
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
    public Item getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursionHelper(sentinel.next, index);
    }

    /**
     * A helper method of getRecursion().
     */
    private Item getRecursionHelper(StuffNode s, int index) {
        if (index == 0) {
            return s.item;
        }
        return getRecursionHelper(s.next, index - 1);
    }
}
