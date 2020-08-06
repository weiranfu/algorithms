package Greedy.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 区间合并
 * 1. 按区间左端点排序
 * 2. 从左到右扫描，并合并可以合并的区间
 */
public class IntervalMerge {

    public List<int[]> mergeIntervals(int[][] ranges) {
        int n = ranges.length;
        List<int[]> res = new ArrayList<>();
        if (n == 0) return res;

        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        int start = Integer.MIN_VALUE, end = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (end < ranges[i][0]) {
                if (end != Integer.MIN_VALUE) {
                    res.add(new int[]{start, end});
                }
                start = ranges[i][0];
                end = ranges[i][1];
            } else {
                end = Math.max(end, ranges[i][1]);
            }
        }
        if (end != Integer.MIN_VALUE) {
            res.add(new int[]{start, end});
        }
        return res;
    }
}
