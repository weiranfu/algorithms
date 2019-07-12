import org.junit.Test;
import static org.junit.Assert.*;


public class SelectionSortTest {

    /** Test SelectionSort(). */
    @Test
    public void testSelectionSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        SelectionSort.sort(input);
        assertArrayEquals(input, expected);
    }

    /** Test findSmallest(). */
    @Test
    public void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;

        int actual = SelectionSort.findSmallest(input, 0);
        assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 2;

        int actual2 = SelectionSort.findSmallest(input2, 2);
        assertEquals(expected2, actual2);
    }

    /** Test swap(). */
    @Test
    public void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        SelectionSort.swap(input, a, b);
        assertArrayEquals(expected, input);
    }
}