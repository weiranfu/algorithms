package Deque_Interface;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertFalse(offByOne.equalChars('z', 'a'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('@', 'A'));
        assertTrue(offByOne.equalChars('`', 'a'));
        assertTrue(offByOne.equalChars('z', '{'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('A', 'C'));
        assertFalse(offByOne.equalChars('c', '3'));
        assertFalse(offByOne.equalChars('x', 'z'));
        assertFalse(offByOne.equalChars('Z', 'X'));
        assertFalse(offByOne.equalChars('1', '3'));
        assertFalse(offByOne.equalChars('?', '!'));
        assertFalse(offByOne.equalChars('`', 'A'));
        assertFalse(offByOne.equalChars(' ', '"'));

    }
}
