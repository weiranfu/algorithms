package Greedy.Interval;

import java.util.Arrays;

/**
 * 最少区间选点
 * <p>
 *     给定N个闭区间[ai,bi]，请你在数轴上选择尽量少的点，使得每个区间内至少包含一个选出的点。
 *     输出选择的点的最小数量。
 * </p>
 * <p>
 *     实际问题：用最少的箭射爆所有气球
 * </p>
 * <p>
 *     算法思路：
 *     将所有区间按照 右端点 从小到大排序
 *     从前往后枚举每个区间
 *     如果当前区间已经包含点，直接pass，否则选择当前区间的右端点，结果加一
 * </p>
 * <p>
 *     证明:
 *     我们按照上面的算法，选择的点是两两之间没有交集的区间的右端点，如果我们想把每个没有交集的区间都覆盖掉的话，必然要都选择上。
 * </p>
 */
public class Interval_PointSelect {

    public int minSelect(int[][] ranges) {
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
