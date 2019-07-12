public class SelectionSortTest {

    /** Test SelectionSort(). */
    public void SelectionSortTest() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        SelectionSort.sort(input);
        org.junit.Assert.assertArrayEquals(input, expected);
    }

    /** Test findSmallest(). */
    public void findSmallestTest() {
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;

        int actual = SelectionSort.findSmallest(input, 0);
        org.junit.Assert.assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 2;

        int actual2 = SelectionSort.findSmallest(input2, 2);
        org.junit.Assert.assertEquals(expected2, actual2);
    }

    /** Test swap(). */
    public void swapTest() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        SelectionSort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public void main(String[] args) {
        findSmallestTest();
        swapTest();
        SelectionSortTest();
    }
}