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
 * 将所有区间按照 右端点 从小到大排序
 *  从前往后枚举每个区间
 *  如果当前区间已经包含点，直接pass，否则选择当前区间的右端点，结果加一
 * 证明：
 *  如果要求区间不相交，则下一个区间的左端点必然在当前 end 的右边，我们需要贪心的选择右端点最小的区间。
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
