package Greedy.AbsoluteInequation;

import java.util.Arrays;

/**
 * 仓库选址
 * 在一条数轴上有 N 家商店，它们的坐标分别为 A1~AN。
 * 求把货仓建在何处，可以使得货仓到每家商店的距离之和最小。
 *
 * 绝对值不等式
 * f(x) = |x - A1| + |x - A2| + ... + |x - An|
 *
 * 最小值应该是 x 取 A1, A2, ..., An 的中位数。如果 n 为偶数，则 x 取 中间两个数之间的任意一点
 *
 * 证明：
 * 将 n 个数分组
 * f(x) = (|x - A1| + |x - An|) + (|x - A2| + |x - An-1|) + ...
 * 考虑每个组里面的两个点 a 和 b
 * 1. x < a < b
 *    x 在 x == a 时取最小
 * 2. a < x < b
 *    x 怎么取，值不变，都是 b - a
 * 3. a < b < x
 *    x 在 x == b 时取最小
 * 所以 x 应该取到 [a, b] 之间的
 */
public class ABS_Inequality {

    public int minCost(int[] pos) {
        int n = pos.length;

        Arrays.sort(pos);

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(pos[i] - pos[n / 2]);
        }
        return res;
    }
}
