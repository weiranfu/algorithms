package DP.Interval_DP;

/**
 * 最少给字符串刷上色的次数，获得目标字符串相应的颜色
 * target = aa bb aa cdc aa
 * 最少四次
 * 1：      aa aa aa aaa aa
 * 2:       aa bb aa aaa aa
 * 3:       aa bb aa ccc aa
 * 4:       aa bb aa cdc aa
 * 因为我们上色的时候可以跨区间一次性上色，传统区间dp不适用。
 * dp[l][r] 为考虑区间 [l, r] 上色的最少次数
 * 观察 l 和 切分位置 i 一起转移的情况
 *
 * dp[l][r] = dp[l + 1][r] + 1    如果 l 单独上色
 *
 * if s[l] = s[i]:            如果l 可以和 切分位置 i 一起上色，包括右端点 r
 *      dp[l][r] = min{ dp[l][r], dp[l+1][i] + dp[i+1][r] } (i >= l+1 && i <= r)  可以包括 r， 即不分区）
 *
 * 举个例子
 * a b a c a
 *
 * dp[1][5] = min{ dp[2][5] + 1, dp[2][3] + dp[4][5], dp[2][5] + dp[6][5](不存在)} = dp[2][5]
 * dp[2][5] = dp[3][5] + 1
 * dp[3][5] = min{ dp[4][5] + 1, dp[4][5] + dp[6][5](不存在)} = dp[4][5]
 * dp[4][5] = dp[5][5] + 1
 */
public class StringPaint {
    int maxn = 305;
    int n;
    char[] str = new char[maxn];
    int[][] dp = new int[maxn][maxn];

    public int paint(int n) {
        this.n = n;

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;                // initialize
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 1, j = len; j <= n; i++, j++) {
                int min = dp[i + 1][j] + 1;                // paint it
                for (int k = i + 1; k <= j; k++) {
                    if (str[i - 1] == str[k - 1]) {
                        min = Math.min(min, dp[i + 1][k] + (k + 1 <= j ? dp[k + 1][j] : 0));
                    }
                }
                dp[i][j] = min;
            }
        }

        return dp[1][n];
    }


    /**
     * 法2：
     * dp[l][r] 为考虑区间 [l, r] 上色的最少次数
     * 比较 区间两个端点的 颜色决定是否一起上色
     * if s[l] == s[r]:
     *      dp[l][r] = min{ dp[l+1][r], dp[l][r-1] }
     * else:
     *      dp[l][r] = min{ dp[l][k] + dp[k+1][r] for k in [l, r-1]}
     */
    public int paint2(int n) {
        this.n = n;

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;                // initialize
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 1, j = len; j <= n; i++, j++) {
                if (str[i - 1] == str[j - 1]) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
                } else {
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }

        return dp[1][n];
    }
}
