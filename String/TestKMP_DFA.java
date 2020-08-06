package String;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestKMP_DFA {
    @Test
    public void test() {
        String text =    "ABABDABACDABABCA";
        String pattern = "ABABCA";
        assertEquals(10, KMP_DFA.search(text, pattern));
    }

    @Test
    public void test1() {
        String text =    "ababcabababb";
        String pattern = "ababb";
        assertEquals(7, KMP_DFA.search(text, pattern));
    }
}
