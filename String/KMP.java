package String;

import java.util.ArrayList;
import java.util.List;

/**
 * The key is to store the max length of common prefix and suffix in pattern string.
 * Use next array to store this information.
 * Match pattern string with itself to construct next array.
 */
public class KMP {
    /**
     * Next[i] means how long the max common prefix and suffix is before index i.
     *
     * 即 模式串自己和自己匹配的最大长度
     *
     * Next[] array starts with 0 and 0. (因为 0 和 1 位置上不匹配都只能退回 0）
     * For pattern "bbbbabb"              b b b b a b b
     * We want to get the next array as [0,0,1,2,3,0,1]
     */

    public static List<Integer> search(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int m = s.length();
        int n = p.length();

        int[] next = new int[n + 1];
        for (int i = 1, j = 0; i < n; i++) {                            // starts at 1
            while (j > 0 && p.charAt(i) != p.charAt(j)) j = next[j];
            if (p.charAt(i) == p.charAt(j)) j++;
            next[i + 1] = j;                                            // write next[] value to next pos
        }

        for (int i = 0, j = 0; i < m; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) j = next[j];
            if (s.charAt(i) == p.charAt(j)) j++;
            if (j == n) {
                res.add(i - n + 1);   // collect one match
                j = next[j];
            }
        }
        return res;
    }
}
