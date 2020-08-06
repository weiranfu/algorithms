package String;

/**
 * Store the max length of common prefix and suffix of a pattern.
 * We can maintain a DFA(Deterministic Finite Automation) to achieve this.
 * Every character is a state(from left to right chars forming a prefix)
 * If we find a match on char c, go to next state.
 * If we find a mismatch on char c, go back to a previous state and restart matching.
 * Construct the DFA:
 * Use X to store the suffix state. (we're finding max length common suffix and prefix)
 * 1. copy dfa[ , X] to dfa[ , j]  // mismatch
 * 2. dfa[c, j] = j + 1            // match
 * 3. X = dfa[c, X]                // update X to next state.
 * DFA diagram: init -> a -> b -> b -> a -> b   for pattern abbab
 */
public class KMP_DFA {
    private static int[][] DFA(String pattern) {
        int n = pattern.length();
        int[][] dfa = new int[128][n];
        dfa[pattern.charAt(0)][0] = 1;
        int x = 0;
        for (int i = 1; i < n; i++) {      // Must start from 1. (suffix)
            for (int j = 0; j < 128; j++) {
                dfa[j][i] = dfa[j][x];
            }
            dfa[pattern.charAt(i)][i] = i + 1;
            x = dfa[pattern.charAt(i)][x];
        }
        return dfa;
    }

    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[][] dfa = DFA(pattern);
        int i = 0, j = 0;
        while (i < n && j < m) {
            j = dfa[text.charAt(i)][j];
            i++;
        }
        if (j == m) {         // if find
            return i - m;
        } else {              // not find
            return -1;
        }
    }
}
