package Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 单调队列
 * 求滑动窗口里面的最大值(decreasing)或最小值(increasing)
 *
 * 为了判断什么时候栈里的值超出了滑动窗口，我们在栈里面存数的index
 */
public class MonotonicQueue {

    /* 有一个大小为k的滑动窗口，它从数组的最左边移动到最右边。*/
    public List<Integer> findLeftMin(int[] a, int k) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!q.isEmpty() && q.peekFirst() < i - k + 1) q.pollFirst();  // index 超过 k 窗口
            while (!q.isEmpty() && a[q.peekLast()] >= a[i]) q.pollLast();  // 求最小值
            q.addLast(i);
            if (i >= k - 1) res.add(a[q.peekFirst()]);                     // 形成窗口
        }
        return res;
    }
}
