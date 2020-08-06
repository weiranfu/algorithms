package DP.Interval_DP;

/**
 * Longest Palindrome Subsequences
 * dp[l][r] 表示区间 [l,r] 中最长回文子序列
 * if s[l] == s[r]:
 *      dp[l][r] = dp[l+1][r-1] + 2
 * else:
 *      dp[l][r] = max{ dp[l+1][r], dp[l][r-1] }
 *
 * 如果要空间优化 可以用len 代替 r
 * dp[len][l]
 *
 * if s[l] == s[r]:
 *      dp[len][l] = dp[len - 2][l+1] + 2
 * else:
 *      dp[len][l] = max{ dp[len - 1][l+1], dp[len - 1][l] }
 *
 * 此时我们可以用长度为 3 的滚动数组实现 空间优化
 */
public class LPS {

    public int lps(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + (len == 2 ? 0 : dp[i + 1][j - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }


}
