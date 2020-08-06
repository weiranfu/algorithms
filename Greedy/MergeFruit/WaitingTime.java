package Greedy.MergeFruit;

import java.util.Arrays;

/**
 * 排队打水
 * 有 n 个人排队到 1 个水龙头处打水，第 i 个人装满水桶所需的时间是 ti，
 * 请问如何安排他们的打水顺序才能使所有人的等待时间之和最小？
 *
 * t1 t2 t3 t4 t5
 * 等待时间: t1 * (n-1) + t2 * (n-2) + t3 * (n-3) +...
 *
 * 越靠后，乘的权重越小，所以我们要将时间长的放到后面去。
 */
public class WaitingTime {

    public int minWaitingTime(int[] times) {
        int n = times.length;

        Arrays.sort(times);

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += times[i] * (n - 1 - i);
        }
        return res;
    }
}
