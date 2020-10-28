package DP.Interval_DP;

import java.util.Arrays;

/**
 * Count the number of palindrome subsequences
 * dp[l][r] 表示区间 [l, r] 中的 回文子序列的个数
 *
 * 根据容斥原理：
 * dp[l][r] = dp[l+1][r] + dp[l][r-1] - dp[l+1][r-1]  两头回文串只考虑其中一个
 * if (s[l] == s[r]):
 *      dp[l][r] += dp[l+1][r-1] + 1     包括两头的回文串 + 中间是空串
 */
public class CountLPS {

    public int count_lps(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - (len == 2 ? 0 : dp[i + 1][j - 1]);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] += (len == 2 ? 0 : dp[i + 1][j - 1]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }
}
