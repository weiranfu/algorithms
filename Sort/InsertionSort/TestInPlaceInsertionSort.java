package Sort.InsertionSort;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestInPlaceInsertionSort {

    @Test
    public void test() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        InPlaceInsertionSort.sort(input);
        assertArrayEquals(input, expected);
    }

    @Test
    public void test1() {
        String[] input = {"e", "h", "i", "g", "f", "b", "c", "a", "d"};
        String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        InPlaceInsertionSort.sort(input);
        assertArrayEquals(input, expected);
    }
}
