package Map.MapInterface;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Aranne
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /** Key-Value pair. */
    private class Entry {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayList<ArrayList<Entry>> buckets;
    private HashSet<K> keySet;
    private int size;
    private int numOfBuckets;

    private int loadFactor() {
        return size / numOfBuckets;
    }

    public MyHashMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numOfBuckets = DEFAULT_SIZE;
        for (int i = 0; i < numOfBuckets; i += 1) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key, int capacity) {
        if (key == null) {
            return 0;
        }
        return Math.floorMod(key.hashCode(), capacity);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls on get() with a null key.");
        }
        int hashIndex = hash(key, numOfBuckets);
        for (Entry e : buckets.get(hashIndex)) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Calls on put() with a null key.");
        }
        if (value == null) {
            remove(key);
            return;
        }
        // If key exists.
        if (keySet().contains(key)) {
            update(key, value);
            return;
        }
        if (loadFactor() > MAX_LF) {
            resize(numOfBuckets * 2);
        }
        int hashIndex = hash(key, numOfBuckets);
        // If key is exists, we could update value.
        buckets.get(hashIndex).add(new Entry(key, value));
        keySet.add(key);
        size += 1;
    }

    /** Update the value corresponding to the key. */
    private void update(K key, V value) {
        int hashIndex = hash(key, numOfBuckets);
        for (Entry e : buckets.get(hashIndex)) {
            if (e.key.equals(key)) {
                e.value = value;
            }
        }
    }

    /** Resize the Map buckets. */
    private void resize(int capacity) {
        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < capacity; i += 1) {
            newBuckets.add(new ArrayList<>());
        }
        for (K key : keySet()) {
            int hashIndex = hash(key, capacity);
            newBuckets.get(hashIndex).add(new Entry(key, get(key)));
        }
        numOfBuckets = capacity;
        buckets = newBuckets;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls on remove() with a null key.");
        }
        if (keySet().contains(key)) {
            int hashIndex = hash(key, numOfBuckets);
            for (Entry e : buckets.get(hashIndex)) {
                if (e.key.equals(key)) {
                    buckets.get(hashIndex).remove(e);
                    keySet.remove(e.key);
                    size -= 1;
                    if (loadFactor() <= 0.25 && numOfBuckets > DEFAULT_SIZE) {
                        resize(numOfBuckets / 2);
                    }
                    return e.value;
                }
            }
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Calls on remove() with a null key.");
        }
        if (keySet.contains(key)) {
            int hashIndex = hash(key, numOfBuckets);
            for (Entry e : buckets.get(hashIndex)) {
                if (e.key.equals(key) && e.value.equals(value)) {
                    buckets.get(hashIndex).remove(e);
                    keySet.remove(e.key);
                    size -= 1;
                    if (loadFactor() <= 0.25 && numOfBuckets > DEFAULT_SIZE) {
                        resize(numOfBuckets / 2);
                    }
                    return e.value;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
