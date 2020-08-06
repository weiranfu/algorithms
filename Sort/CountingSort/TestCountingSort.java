package Sort.CountingSort;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCountingSort {

    @Test
    public void test() {
        int[] input = {95, 94, 91, 98, 99, 90, 99, 93, 91, 92};
        int[] expect = {90, 91, 91, 92, 93, 94, 95, 98, 99, 99};
        CountingSort.sort(input);
        assertArrayEquals(input, expect);
    }
}
