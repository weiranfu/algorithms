import java.util.List;

public interface Map61B<K, V> {
    /** Returns true if this map contains a mapping for the specified key.
     */
    boolean containsKey(K key);

    /** Associates the specified value with the specified key in this map.
     Throws an IllegalArgumentException if the key is null. */
    void put(K key, V value);

    /** Returns corresponding value of a key. */
     V get(K key);

    /** Returns a list of keys. */
    List<K> keys();

    /** Returns the number of key-value mappings in this map. */
    int size();
}
