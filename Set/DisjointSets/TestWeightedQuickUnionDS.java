package Set.DisjointSets;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestWeightedQuickUnionDS {
    @Test
    public void test() {
        WeightedQuickUnionDS uf = new WeightedQuickUnionDS(5);
        assertFalse(uf.isConnected(0,3));
        uf.connect(0, 3);
        assertTrue(uf.isConnected(0,3));
    }
}
