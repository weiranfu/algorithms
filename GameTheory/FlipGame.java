package GameTheory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 */
public class FlipGame {

    /**
     * We can view the contiguous "+" as an independent impartial game.
     * And this game is only related to the number of contiguous "+".
     * "++--+-+++" can be view as (2),(1),(3) stone piles.
     * Every time we flip "++" into "--" means we take two stones from one pile.
     * How to divide a pile with (5) after taking two stones?
     * (0, 3) (1, 2)      (Mind that (2, 1) (3, 0) is duplicate)
     */
    public boolean canWin(String s) {
        int n = s.length();
        List<Integer> states = new ArrayList<>();
        int max = 0, len = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '+') {
                len++;
            }
            if (i + 1 == n || s.charAt(i) == '-') {     // collect last len
                if (len >= 2) states.add(len);
                max = Math.max(max, len);
                len = 0;
            }
        }
        int[] dp = new int[max + 1];                // to store sg value.
        for (int i = 2; i <= max; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j <= (i - 2) / 2; j++) {// all possible sub-states
                set.add(dp[j] ^ dp[i - j - 2]);     // SG theorem SG(s) = SG(s1) ^ SG(s2)
            }
            for (int j = 0; ; j++) {
                if (!set.contains(j)) {    // find SG value
                    dp[i] = j;
                    break;
                }
            }
        }
        int res = 0;
        for (int state : states) {
            res ^= dp[state];
        }
        return res != 0;
    }
}
