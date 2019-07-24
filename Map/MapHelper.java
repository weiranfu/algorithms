import org.junit.Test;
import static org.junit.Assert.*;

public class MapHelper {
    /** Returns value of Key in map if it exists. */
    public static <K, V> V get(Map61B<K, V> map, K key) {
        return map.get(key);
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
    }
}
