package DP.LCS_LIS;

/**
 * Longest Common Subsequences
 * dp[i][j] 是我们考虑到 i, j 位置 但不一定选了 i, j 上的字符 (我们并不关心尾巴上的字符是否匹配）
 *
 * A[i] == B[j]:
 *      dp[i][j] = dp[i-1][j-1] + 1
 * else:
 *      dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }
 */
public class LCS {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
