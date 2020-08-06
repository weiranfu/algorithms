package Deque_Interface;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    int n = 5;
    CharacterComparator cc = new OffByN(n);

    @Test
    public void testEqualChars() {
        assertTrue(cc.equalChars('a', 'f'));

        assertTrue(cc.equalChars('f', 'a'));

        assertFalse(cc.equalChars('f', 'h'));
    }
}
