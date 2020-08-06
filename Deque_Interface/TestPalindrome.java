package Deque_Interface;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        // Test two strings equal, other than test two Deque equal.
        // Because Deque doesn't have equal() method.
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("parse"));

        assertTrue(palindrome.isPalindrome("noon"));

        assertTrue(palindrome.isPalindrome(""));

        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testIsPalindromeCC() {
        assertTrue(palindrome.isPalindrome("flake", cc));

        assertTrue(palindrome.isPalindrome("this", cc));

        assertFalse(palindrome.isPalindrome("noon", cc));

        assertTrue(palindrome.isPalindrome("a", cc));

        assertTrue(palindrome.isPalindrome("", cc));
    }
}
