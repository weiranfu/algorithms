package Greedy.CowsArrangement;

import java.util.Arrays;

/**
 * N头奶牛每一头都有着自己的重量 Wi 以及自己的强壮程度 Si。
 * 叠罗汉表演时，奶牛们站在彼此的身上，形成一个高高的垂直堆叠。
 * 一头牛支撑不住的可能性取决于它头上所有牛的总重量（不包括它自己）减去它的身体强壮程度的值，现在称该数值为风险值，风险值越大，这只牛撑不住的可能性越高。
 * 确定奶牛的排序，使得所有奶牛的风险值中的 最大值 尽可能的小。
 *
 * 结论：
 * 按照 Wi + Si 从大到小排序，最大的危险系数一定是最小的。
 *
 * 证明：
 * 考虑第 i 头牛和第 i+1 头牛，如果他们是逆序的，则 Wi + Si > Wi+1 + Si+1
 *               第 i 个位置上                      第 i+1 个位置上
 * 交换前    W1+W2+...+Wi-1 - Si                 W1+W2+...+Wi - Si+1
 * 交换后    W1+W2+...+Wi-1 - Si+1               W1+W2+...+Wi+1 - Si
 *
 * 相当于      -Si                                     Wi - Si+1
 *            -Si+1                                   Wi+1 - Si
 *
 * 相当于      Si+1                                    Wi + Si
 *            Si                                      Wi+1 + Si+1
 *
 * 因为 Wi + Si > Wi+1 + Si+1, 所以 Wi + Si > max(Si, Wi+1 + Si+1)
 * 所以如果两者逆序，则交换后，最大值能变小。
 */
public class CowsArrangement {

    /* cows[i] = { weight, strong } */
    public int minRisk(int[][] cows) {
        int n = cows.length;

        Arrays.sort(cows, (a, b) -> a[0] + a[1] - b[0] - b[1]);

        int sum = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, sum - cows[i][1]);
            sum += cows[i][0];
        }
        return res;
    }
}
