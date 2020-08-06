package Greedy.Interval;

import java.util.Arrays;

/**
 * 最少区间覆盖
 * 给定N个闭区间[ai,bi]以及一个线段区间[s,t]，请你选择尽量少的区间，将指定线段区间完全覆盖。
 * 输出最少区间数，如果无法完全覆盖则输出-1。
 *
 * 算法思路：
 * 将所有区间按 左端点 从小到大排序
 * 从前往后依次枚举每个区间，在所有能覆盖 start 的区间中选择 右端点 最大的，然后将 start 更新成右端点的最大值
 */
public class Interval_Cover {

    public int minCover(int[][] ranges, int start, int end) {
        int n = ranges.length;
        int INF = 0x3f3f3f3f;

        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        int cnt = 0;
        boolean success = false;
        for (int i = 0; i < n; i++) {
            int j = i, r = -INF;
            while (j < n && ranges[j][0] <= start) {
                r = Math.max(r, ranges[j][1]);
                j++;
            }

            cnt++;                      // use an max right end interval

            if (r < start) {            // if cannot reach start interval
                cnt = -1;
                break;
            }

            if (r >= end) {             // cover all interval
                success = true;
                break;
            }

            start = r;                  // update start
            i = j - 1;                  // update i to j - 1
        }

        return success ? cnt : -1;      // it is possible that there's not enough ranges to cover whole interval after for loop.
    }

}
