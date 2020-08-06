package Tree.BIT;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestBIT2D {
    @Test
    public void test() {
        int[][] nums = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        BIT_2D bit2d = new BIT_2D(nums);
        assertEquals(bit2d.rangeSum(2, 1, 4, 3), 8);
        bit2d.alter(3, 2, 2);
        assertEquals(bit2d.rangeSum(2, 1, 4, 3), 10);
    }
}
