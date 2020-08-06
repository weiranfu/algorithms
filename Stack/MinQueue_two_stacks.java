package Stack;

public class MinQueue_two_stacks {

    MinStack input = new MinStack();      // using MinStack in this package
    MinStack output = new MinStack();

    public void add(int val) {
        input.push(val);
    }

    public int pop() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    public int findMin() {
        if (input.isEmpty() || output.isEmpty()) {
            return input.isEmpty() ? output.findMin() : input.findMin();
        }
        return Math.min(input.findMin(), output.findMin());
    }
}
