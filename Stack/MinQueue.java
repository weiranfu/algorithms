package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Find the minimum value of a queue in O(1)
 * The key idea is to only store the items in the queue that are needed to determine the minimum.
 * We store the index for each element in the queue.
 * And we also remember how many elements we already have added and removed.
 * https://cp-algorithms.com/data_structures/stack_queue_modification.html
 */
public class MinQueue {

    Deque<int[]> queue = new ArrayDeque<>();
    int addId = 0;
    int removeId = 0;

    public void add(int val) {
        while (!queue.isEmpty() && queue.peekLast()[0] > val) {
            queue.pollLast();
        }
        queue.addLast(new int[]{val, addId});
        addId++;
    }

    public void poll() {
        if (!queue.isEmpty() && queue.peekFirst()[1] == removeId) {
            queue.pollFirst();
        }
        removeId++;
    }

    public int findMin() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.peekFirst()[1];
    }
}
