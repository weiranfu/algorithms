package HashTable;

/**
 * Create an ArrayList of type boolean and size 2 billion to make store and search data faster.
 * The add(int x) method simply sets the x position in our ArrayList to true. This takes Θ(1) time.
 * The contains(int x) method simply returns whether the x position in our ArrayList is true or false.
 * This also takes Θ(1) time!
 */
public class DataIndexedIntegerSet {
    private boolean[] present;

    public DataIndexedIntegerSet() {
        present = new boolean[2000000000];
    }

    /** Add a new item to the Set, which takes Θ(1) time. */
    public void add(int x) {
        present[x] = true;
    }

    /** Search an item in the Set, which takes Θ(1) time. */
    public boolean contains(int x) {
        return present[x];
    }
}
