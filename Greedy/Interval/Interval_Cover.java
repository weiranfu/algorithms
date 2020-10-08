package Greedy.Interval;

import java.util.Arrays;

/**
 * 最少区间覆盖
 * 给定N个闭区间[ai,bi]以及一个线段区间[s,t]，请你选择尽量少的区间，将指定线段区间完全覆盖。
 * 输出最少区间数，如果无法完全覆盖则输出-1。
 *
 * 实际问题： 花园喷泉覆盖整个区间，青蛙用最少步数跳到终点 jump game
 *
 * 算法思路：
 * 将所有区间按 左端点 从小到大排序
 * 从前往后依次枚举每个区间，在所有能覆盖 start 的区间中选择 右端点 最大的，然后将 start 更新成右端点的最大值
 * O(nlogn)
 */
public class Interval_Cover {
    int INF = 0x3f3f3f3f;

    public int minCover(int[][] ranges, int start, int end) {
        int n = ranges.length;
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int j = i, r = -INF;
            while (j < n && ranges[j][0] <= start) {
                r = Math.max(r, ranges[j][1]);
                j++;
            }
            cnt++;                      // choose an interval with max right boundary
            if (r <= start) return -1;  // if cannot reach start interval
            if (r >= end) return cnt;   // cover all interval

            start = r;                  // update start
            i = j - 1;                  // update i to j - 1
        }

        return -1;      // it is possible that there's not enough ranges to cover whole interval after for loop.
    }

    /**
     * 方法2: 不用 sort 区间
     * 如果 [start, end] 区间不是特别大的情况下，转化为 jump game problem
     * [start, end] 里每一个点有一个最远能覆盖的距离，即 jump ability
     * 然后从前往后依次扫描每个点，在当前 end 区间内找的最远的能覆盖的点，更新end
     * O(n)
     */
    public int minCover2(int[][] ranges, int start, int end) {
        int L = end - start + 1;
        int[] jump = new int[L];
        for (int[] range : ranges) {
            int l = Math.max(start, range[0]) - start;      // left boundary
            int r = Math.min(end, range[1]) - start;        // right boundary
            jump[l] = Math.max(jump[l], r);                 // update jump ability
        }
        int cnt = 0, curr = 0;
        for (int i = 0; i < L; i++) {                       // 扫描 [0, L-1] 中的每一个点 即 [start, end] 中每一个点
            int j = i, e = -INF;
            while (j < L && j <= curr) {
                e = Math.max(e, jump[j++]);
            }
            cnt++;
            if (e <= curr) return -1;            // cannot reach curr
            if (e >= L - 1) return cnt;          // cover [0，L-1]
            curr = e;
            i = j - 1;
        }
        return -1;
    }

}
