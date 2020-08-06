package String;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRabinKarp {
    @Test
    public void testSearch() {
        String text =    "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        assertEquals(10, RabinKarp.search(text, pattern));
    }

    @Test
    public void testSearch1() {
        String text =    "ababcabababb";
        String pattern = "ababb";
        assertEquals(7, RabinKarp.search(text, pattern));
    }
}
