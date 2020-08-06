package Sort.InsertionSort;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestShellSort {

    @Test
    public void test() {
        String[] input = {"e", "h", "i", "g", "f", "b", "c", "a", "d"};
        String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        ShellSort.sort(input);
        assertArrayEquals(input, expected);
    }
}
