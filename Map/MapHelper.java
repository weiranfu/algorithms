import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapHelper {
    /** Returns value of Key in map if it exists. */
    public static <K, V> V get(Map61B<K, V> map, K key) {
        return map.get(key);
    }

    /** Returns the maximum of all keys. Works only if keys are comparable.
     * extends keyword is a type upper bound here.
     * Meaning every ArrayMap you give me must have a actual parameter type that is a subtype of Comparable<T>.
     */
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {
        if (map.size() == 0) {
            return null;
        }
        List<K> keys = map.keys();
        K maxKey = keys.get(0);
        for (int i = 0; i < keys.size(); i += 1) {
            if (maxKey.compareTo(keys.get(i)) < 0) {
                maxKey = keys.get(i);
            }
        }
        return maxKey;
    }

    @Test
    public void testGet() {
        Map61B<String, Integer> am = new ArrayMap<>();
        am.put("horse", 3);
        am.put("tiger", 1);
        am.put("fish", 5);
        int expected = 1;
        int actual = MapHelper.get(am, "tiger");
        assertEquals(expected, actual);
        assertNull(MapHelper.get(am, "dafjdfanfg"));
    }

    @Test
    public void testMaxKey() {
        Map61B<String, Integer> am = new ArrayMap<>();
        am.put("horse", 3);
        am.put("tiger", 1);
        am.put("fish", 5);

        String expected2 = "tiger";
        String actual2 = MapHelper.maxKey(am);
        assertEquals(expected2, actual2);

        assertNull(MapHelper.maxKey(new ArrayMap<String, Integer>()));
    }
}
