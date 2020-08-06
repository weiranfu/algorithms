package DP.Integer_Break;

/**
 * 一个正整数n可以表示成若干个正整数之和，形如：n=n1+n2+…+nk，其中n1≥n2≥…≥nk,k≥1。
 * 我们将这样的一种表示称为正整数n的一种划分。
 * 现在给定一个正整数n，请你求出n共有多少种不同的划分方法。
 *
 * dp[i][j] 表示 整数 i 被恰好分成 j 份的方案数
 * 两种情况
 * 所有方案中若某一方案最小值大于1，我们可以把其中所有元素都减1，并不影响方案总数
 *      dp[i][j] = dp[i-j][j]
 * 所有方案中若某一方案最小值为1，我们可以把这个1单独拎出来，把剩下 i-1 分成 j-1 份
 *      dp[i][j] = dp[i-1][j-1]
 *
 * dp[i][j] = dp[i-1][j-1] + dp[i-j][j]
 *
 * ans = dp[n][1] + dp[n][2] + dp[n][3] +...+ dp[n][n]
 *
 * dp[i][j] 表示 整数 i 被拆分的最大数不超过 j
 *
 * if i >= j:
 *      dp[i][j] = dp[i-j][j] + dp[i][j-1]    (包含 j 和不包含 j)
 * else:
 *      dp[i][j] = dp[i][i]
 */
public class IntegerBreak {

    int mod = (int)1e9 + 7;
    int maxn = 1005;
    int[][] dp1 = new int[maxn][maxn];
    int[] dp = new int[maxn];
    int n = 1000;

    /**
     *    把一个正整数分成若干个正整数之和，可以重复
     */
    /* dp[i][j] 表示 整数 i 被恰好分成 j 份的方案数 */
    public int int_break_1() {

        dp1[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {     // i 分成 j 份
                dp1[i][j] = (dp1[i - 1][j - 1] + dp1[i - j][j]) % mod;
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = (res + dp1[n][i]) % mod;
        }
        return res;
    }

    /* 背包解法 */
    public int int_break_1_knapsack() {

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {             // i 个 物品
            for (int j = i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - i]) % mod;
            }
        }
        return dp[n];
    }

    /**
     *   把一个正整数分成若干正整数只和，不可重复
     */
    /* 背包解法 */
    public int int_break_2() {

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= i; j--) {
                dp[j] = (dp[j] + dp[j - i]) % mod;
            }
        }
        return dp[n];
    }

    /**
     *   把一个正整数分成若干份正整数只和，且每个正整数不超过 k
     */
    /* 背包解法 */
    public int int_break_3(int K) {

        dp[0] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - i]) % mod;
            }
        }
        return dp[n];
    }

    /**
     *   把一个正整数分成 K 份正整数只和
     */
    /* dp[i][j] 表示 整数 i 被恰好分成 j 份的方案数 */
    public int int_break_4(int K) {

        dp1[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {  // i 分成 j 份
                dp1[i][j] = (dp1[i - 1][j - 1] + dp1[i - j][j]);
            }
        }
        return dp1[n][K];
    }

    /**
     *   把一个正整数分成若干 奇数 只和
     */
    /*  背包解法 */
    public int int_break_5() {

        dp[0] = 1;
        for (int i = 1; i <= n; i += 2) {  // 奇数 物品
            for (int j = i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - i]) % mod;
            }
        }
        return dp[n];
    }
}
