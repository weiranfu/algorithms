package Greedy.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 安排课程
 * 给定 n 个闭区间[ti,di],  ti 表示该课程的持续时间，di 表示上完该课程的 deadline，
 * 怎样合理安排这些课程，使得尽量多的课程能在 deadline 前上完。
 *
 * 算法思路
 * 1. 将课程按照 deadline 排序
 * 2. 从左往右考虑每个课程，并记录所有课程所用时间 time
 *    如果 time + 当前课程时间 ti <= di, 我们可以上这个课，总数+1
 *    否则，我们从已经上过的课里面找到 时间最长的课 A 尝试与当前的课 Curr 交换，
 *         如果 A 的时间大于 Curr 的时间，选择交换，因为我们可以减少总的上课时长，同时因为按照 deadline 排序了，不用担心 deadline 超过
 *         如果 A 的时间小于 Curr 的时间，不选择交换，因为会增加总的时长，会对之后的课程 C 是否超过其 deadline 产生影响。
 * 我们通过一个 最大堆 来维护上过的课的时长。
 */
public class Interval_Schedule {

    /**
     * @param courses  courses[i] = { duration, deadline }
     */
    public int maxCourse(int[][] courses) {
        int n = courses.length;

        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        int time = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                pq.add(courses[i][0]);
            } else if (!pq.isEmpty() && pq.peek() >= courses[i][0]) {  // 最大时长 大于等于 当前时长则 选择交换
                time = time - pq.peek() + courses[i][0];
                pq.poll();
                pq.add(courses[i][0]);
            }
        }
        return pq.size();
    }
}
