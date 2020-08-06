package TwoPointers;

import java.util.ArrayList;
import java.util.List;

/** Parse a string into words
 * Different words is separated by space.
 */
public class ParseStr {

    public List<String> parseWords(String line) {
        List<String> res = new ArrayList<>();
        int n = line.length();

        for (int i = 0; i < n; i++) {
            while (i < n && line.charAt(i) == ' ') i++;

            int j = i;
            while (j < n && line.charAt(j) != ' ') j++;

            res.add(line.substring(i, j));

            i = j - 1;              // mind i = j - 1, i++ equals i = j
        }
        return res;
    }
}
