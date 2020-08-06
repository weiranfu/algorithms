package Stack;

import java.util.Stack;

/**
 * We will modify a stack in a way that allows up to find the smallest element of the stack in O(1).
 * To do this, we will not only store the elements in the stack, but we will store them in pairs:
 * the element itself and the minimum in the stack starting from this element and below.
 */
public class MinStack {

    Stack<int[]> stack = new Stack<>();

    public void push(int val) {
        int newMin = stack.isEmpty() ? val : Math.min(stack.peek()[1], val);
        stack.push(new int[]{val, newMin});
    }

    public int pop() {
        int res = stack.peek()[0];
        stack.pop();
        return res;
    }

    public int findMin() {
        return stack.peek()[1];
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
