package Greedy.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最小区间分组
 * 给定N个闭区间[ai,bi]，请你将这些区间分成若干组，使得每组内部的区间两两之间（包括端点）没有交集，并使得组数尽可能小。
 *
 * 实际问题：公司今天有20场会议，问最少用几个会议室可以安排下
 *
 * 算法思路：
 * 将所有区间按照 左端点 从小到大排序
 * 从前往后处理每个区间, 所以每个后面的区间左端点都大于前面区间的左端点
 *   判断能否将其放到某个现有的组中（当前区间的 左端点 是否在某个组的最后一个区间的 右端点 右侧，即是否相交，L[i] > Max_r）
 *   1. 如果不存在这样的组，则开新组，将其放入
 *   2. 如果存在一个或多个这样的组，随便选一个将其放入并更新 Max_r (一般用 最小堆 来维护 max_r，选择右端点max_r最小的 加入并更新)
 *      (因为区间是按左端点排过序的，之后的区间左端点一定大于当前区间左端点，如果当前区间可以有 x 种加入的选择方案，后面的区间
 *      也一定有 x 种加入的选择方案，所以我们一般选择右端点最小的加入)
 *
 *   ---- ------  --------     --------
 *            -------       ----
 *                 ----       -----
 */
public class Interval_Grouping {

    public int minGroup(int[][] ranges) {
        int n = ranges.length;

        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);         // 左端点排序

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // maintain max_r
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty() || pq.peek() >= ranges[i][0]) { // 如果 pq 里面最小的 max_r 都大与当前区间的左端点
                pq.add(ranges[i][1]);                        // 则新开一个组
            } else {
                pq.poll();
                pq.add(ranges[i][1]);                        // 更新 max_r 最小的组的最右端点
            }
        }

        return pq.size();
    }
}
