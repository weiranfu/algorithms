package Stack;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMinStack {
    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(4);
        minStack.push(1);
        assertEquals(minStack.findMin(), 1);
        minStack.pop();
        assertEquals(minStack.findMin(), 2);
        minStack.pop();
        assertEquals(minStack.findMin(), 2);
    }
}
