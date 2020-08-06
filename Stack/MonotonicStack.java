package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 *
 * 应用：
 * 找出每个数左右两边第一个大于或小于它的解
 * 单调递增栈用于查找两边第一个小于当前元素的值，单调递减栈用于查找两边第一个大于当前元素的值
 * 一般数组中的单调性问题，题目中隐含第一个或离此元素最近的大于或小于元素的值，这类问题都可以考虑下，用单调栈是否可以求解
 */
public class MonotonicStack {

    public List<Integer> findLeftMin(int[] a) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= a[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res.add(stack.peek());
            } else {
                res.add(-1);
            }
            stack.add(a[i]);
        }
        return res;
    }
}
