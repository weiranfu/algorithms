package HashTable;

/**
 * We can write any unique lowercase string in base 26 to avoid collisions.
 * The base 26 representation gives a unique integer to every english word containing lowercase letters,
 * much like using base 10 gives a unique representation to every number.
 */
public class DataIndexedEnglishWordSet {
    private boolean[] present;

    public DataIndexedEnglishWordSet() {
        present = new boolean[2000000000];
    }

    /** Add a new word to Set, which takes Θ(1) time. */
    public void add(String s) {
        present[englishToInt(s)] = true;
    }

    /** Search a word in the Set, which takes Θ(1) time. */
    public boolean contains(String s) {
        return present[englishToInt(s)];
    }

    /** Using base 26 to translate English word to a unique number. */
    private static int englishToInt(String s) {
        int sum = 0;
        // Every time we add a new digit, we multiply 26 first and then add the digit to the sum.
        for (int i = 0; i < s.length(); i += 1) {
            sum = sum * 26;
            sum += letterNum(s, i);
        }
        return sum;
    }

    /** Converts ith character of String to a letter number.
     * e.g. 'a' -> 1, 'b' -> 2, 'z' -> 26 */
    private static int letterNum(String s, int i) {
        int ithChar = s.charAt(i);
        if ((ithChar < 'a') || (ithChar > 'z')) {
            throw new IllegalArgumentException();
        }

        return ithChar - 'a' + 1;
    }
}
