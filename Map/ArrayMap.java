import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayMap<K, V> implements Map61B<K, V>, Iterable<K> {
    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        keys = (K[]) new Object[10];
        values = (V[]) new Object[10];
        size = 0;
    }

    /** Returns the index of the given key if it exists, -1 otherwise. */
    private int keyIndex(K key) {
        for (int i = 0; i < size; i += 1) {
            if (keys[i].equals(key)) {              // using .equals instead of ==
                return i;
            }
        }
        return -1;
    }

    /** Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    /** Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    @Override
    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    /** Returns corresponding value of a key. */
    @Override
    public V get(K key) {
        int index = keyIndex(key);
        /*
        if (index == -1) {
            throw new IllegalArgumentException("The key provided " + key + " was not in ArrayMap.");
        }
        */
        try {
            return values[index];
        } catch (Exception e) {
            System.out.println("An exception detected: " + e);
            return null;
        }
    }

    /** Returns a list of keys. */
    @Override
    public List<K> keys() {
        List<K> list = new ArrayList<>();
        for (int i = 0; i < size; i += 1) {
            list.add(keys[i]);
        }
        return list;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        /* A much easier way is to use the iterator of keyList.
        List<K> keyList = keys();
        return keyList.iterator();
         */
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int p;

        public KeyIterator() {
            p = 0;
        }

        @Override
        public boolean hasNext() {
            return p < size;
        }

        @Override
        public K next() {
            K returnItem = keys[p];
            p += 1;
            return returnItem;
        }
    }

    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<>();
        am.put(2,5);
        int expected = 5;
        assertEquals((Integer) expected, am.get(2));
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> m = new ArrayMap<>();
        m.get("monkey");
        m.put("monkey", 10);
        m.put("tiger", 3);
        m.put("horse", 5);

        System.out.println(m.containsKey("fish"));
        System.out.println(m.get("monkey"));
        System.out.println(m.keys());
        // Test iterator.
        for (String s: m) {
            System.out.println(s);
        }
    }
}