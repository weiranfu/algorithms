package Tree.BIT;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestBIT {

    @Test
    public void test() {
        int[] a = {1, -2, 5, 3, -5};
        BIT bit = new BIT(a);
        assertEquals(bit.sum(1, 4), 1);
        bit.alter(3, 10);
        assertEquals(bit.sum(0, 4), 12);
        assertEquals(bit.sum(3, 4), 8);
        assertEquals(bit.sum(1, 2), 3);
    }
    
}