package Deque_Interface;

public class Palindrome {
    /**
     * Given a String, returns a Deque where the characters appear
     * in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /** Returns true if the given word is a palindrome, false otherwise. */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return helpIsPalindrome(deque);
    }

    /**
     * A helper function to check palindrome.
     * first points to the first item in deque.
     * last points to the last item in deque.
     */
    private boolean helpIsPalindrome(Deque<Character> deque) {
        if (deque.size() < 2) {
            return true;
        }
        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }
        return helpIsPalindrome(deque);
    }

    /**
     * Returns true if the word is a off-by-one palindrome which means
     * the head and tail of this word are not same any more.
     * the difference between head and tail is always 1.
     * e.g. "flake" is an off-by-one palindrome since 'f' and 'e' are one letter apart, and
     * 'k' and 'l' are one letter apart.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return helpIsPalindrome(deque, cc);
    }

    /** A helper function of isPalindromeCC to recursion. */
    private boolean helpIsPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        }
        if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return false;
        }
        return helpIsPalindrome(deque, cc);
    }
}
