package DP.Count_Digit;

/**
 *  求给定两个整数 a 和 b，求 a 和 b 之间的所有数字中0~9的出现次数。
 *
 *  count(int n, int x)   1 ~ n 中 x 出现的次数
 *  [a, b] 中 x 出现的次数  count(b, x) - count(a-1, x)
 *  例如 n = abcdefg, x = 1
 *  求 1 在其每一位上的个数
 *  例如 求 1 在第四位上的个数
 *  1 <= xxx1yyy <= abcdefg
 *
 *  (1) x == 000 ~ abc-1, yyy = 000 ~ 999                   共有 abc * 1000
 *  (2) x == abc,
 *      (2.1) d < 1, abc1yyy > abcdefg                      共有 0
 *      (2.2) d = 1, yyy = 000 ~ efg                        共有 efg + 1
 *      (2.3) d > 1, abc1yyy < abcdefg, yyy = 000 ~ 999     共有 1000
 *
 *  统计 0 在第四位上的个数时，要注意：
 *  (1) x == 001 ~ abc-1, yyy = 000 ~ 999                   共有 (abc-1) * 1000
 */
public class CountDigit {

    // n 有多少位
    int digits(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n /= 10;
        }
        return res;
    }

    // 1 ~ n 中 x 出现的次数
    int count(int n, int x) {
        int res = 0, d = digits(n);
        for (int i = 1; i <= d; i++) {          // 从右到左第j位上数字i出现多少次
            int p = (int)Math.pow(10, i - 1);
            // l和r是第j位左边和右边的整数 (视频中的abc和efg); dj是第j位的数字
            int l = n / p / 10;
            int r = n % p;
            int dj = n / p % 10;
            // 计算第j位左边的整数小于l (视频中xxx = 000 ~ abc - 1)的情况
            if (x > 0) {
                res += l * p;
            } else if (x == 0 && l != 0) {     // 如果i = 0, 左边高位不能全为0(视频中xxx = 001 ~ abc - 1)
                res += (l - 1) * p;
            }
            if (dj > x && (l != 0 || x != 0)) {   // 高位不能同时为 0
                res += p;
            }
            if (dj == x && (l != 0 || x != 0)) {   // 高位不能同时为 0
                res += r + 1;
            }

        }
        return res;
    }

    /**
     * 计算 a, b 之间每一位出现的个数
     */
    public int[] count_digit(int a, int b) {
        int[] res = new int[10];
        if (a > b) return count_digit(b, a);
        for (int i = 0; i <= 9; i++) {
            res[i] = count(b, i) - count(a - 1, i);
        }
        return res;
    }
}
