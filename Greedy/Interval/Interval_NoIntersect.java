package Greedy.Interval;

import java.util.Arrays;

/**
 * 最多不相交区间
 * 给定N个闭区间[ai,bi]，请你在数轴上选择若干区间，使得选中的区间之间互不相交（包括端点）。
 * 输出可选取区间的最大数量。
 *
 * 实际问题：很多课程，或者活动，我们从中选择最多数量的课程或活动参加
 *
 * 算法思路：
 * 和区间选点一样
 */
public class Interval_NoIntersect {

    public int maxSelect(int[][] ranges) {
        int n = ranges.length;

        Arrays.sort(ranges, (a, b) -> a[1] - b[1]);     // 右端点排序

        int cnt = 0, end = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (ranges[i][0] > end) {
                cnt++;
                end = ranges[i][1];
            }
        }
        return cnt;
    }
}
