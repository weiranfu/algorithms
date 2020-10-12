package Deque;

/**
 * Deque means doubled-ended queue, which is a sequence containers with
 * dynamic size that can be expanded or contracted on both ends(front or back).
 * This array based deque is circular, which means
 * nextFirst pointer points at the index that next first item will be put in and
 * nextLast pointer points at the index that next last item will be put in.
 * If nextFirst pointer is at position zero and you addFirst(),
 * nextFirst pointer should look back around to the end of the array. So does nextLast pointer.
 * @param <T>
 * @author aranne
 */
public class ArrayDeque<T> {
    /** This is a generic array based deque. */
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int RFACTOR = 2;
    private static final int INIT_CAPACITY = 8;
    private static final double MIN_USAGE_RATIO = 0.25;

    /** initiate an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY]; // The starting size of this deque.
        size = 0;
        nextFirst = INIT_CAPACITY / 2;
        nextLast = nextFirst + 1;
    }

    /** Compute the index in a circular array. */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** Compute the index in a circular array. */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /** Resize the underlying deque to the target capacity. */
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        // The starting position of items array.
        int curr = plusOne(nextFirst);
        // Copy all items in items array to newArray, in which the starting position is 0.
        for (int i = 0; i < size; i += 1) {
            newArray[i] = items[curr];
            curr = plusOne(curr);
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Adds an item of type Generic to the front of this deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Adds an item of type Generic to the back of this deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in this deque. */
    public int size() {
        return size;
    }

    /** Prints the items in this deque from first to last, separated by a space. */
    public void printDeque() {
        if (size == 0) {
            System.out.println("This is an empty deque.");
        }
        int curr = plusOne(nextFirst);
        // Prints all items in items array.
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[curr] + " ");
            curr = plusOne(curr);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of this deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T firstItem = items[nextFirst];
        items[nextFirst] = null;        // Nulling out the deleted item.
        size -= 1;

        if ((double) size / items.length <= MIN_USAGE_RATIO && items.length >= INIT_CAPACITY * 2) {
            resize(items.length / RFACTOR);
        }

        return firstItem;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T lastItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        if ((double) size / items.length <= MIN_USAGE_RATIO && items.length >= INIT_CAPACITY * 2) {
            resize(items.length / RFACTOR);
        }

        return lastItem;
    }

    /** Gets the item at the given index. If no such item exists, returns null. */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int referIndex = (nextFirst + index + 1) % items.length;
        return items[referIndex];
    }
}
