package String;

import java.util.HashMap;
import java.util.Map;

/**
 * Rabin-Karp Algorithm: https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
 * uses hashing to find an exact match of a pattern string in a text.
 * It uses a rolling hash to quickly filter out positions of the text that cannot match the pattern,
 * and then checks for a match at the remaining positions
 */
public class RabinKarp {

    private static Map<Character, Long> getMap() {
        Map<Character, Long> toLong = new HashMap<>();
        for (long i = 0; i < 26; i++) {
            toLong.put((char)('A' + i), i);
        }
        return toLong;
    }

    public static int search(String text, String pattern) {
        text = text.toUpperCase();
        pattern = pattern.toUpperCase();
        Map<Character, Long> toLong = getMap();

        int n = text.length();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = toLong.get(text.charAt(i));
        }

        long base = 26;
        int L = pattern.length();
        long basePow = (long)Math.pow(base, L);
        // get pattern's hash value
        long hpattern = hash(pattern, base, toLong);

        long hash = 0;
        for (int i = 0; i <= n - L; i++) {
            // compute hash of the current sequence in O(1) time
            String substring = text.substring(i, i + L);
            if (i == 0) {
                // compute hash of the first sequence in O(L) time
                hash = hash(substring, base, toLong);
            } else {
                hash = hash * base - nums[i - 1] * basePow + nums[i + L - 1];
            }
            if (hash == hpattern) {
                if (pattern.equals(substring)) {    // avoid collisions
                    return i;
                }
            }
        }
        return -1;
    }

    private static long hash(String s, long base, Map<Character, Long> toLong) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * base + toLong.get(s.charAt(i));
        }
        return hash;
    }
}
