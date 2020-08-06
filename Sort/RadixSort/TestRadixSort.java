package Sort.RadixSort;

import Sort.InsertionSort.InPlaceInsertionSort;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRadixSort {

    @Test
    public void test() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        RadixSort.sort(input);
        assertArrayEquals(input, expected);
    }
}
