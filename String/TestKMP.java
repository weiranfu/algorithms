package String;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestKMP {

    @Test
    public void testSearch() {
        String text =    "abacabab";
        String pattern = "ab";
        System.out.println(KMP.search(text, pattern));
    }
}
