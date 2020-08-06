package DP.LCS_LIS;

/**
 * Longest Common Increasing Subsequences
 * dp[i][j]  选择了s[i],t[j]字符
 *
 * if s[i] != t[j]:
 *      dp[i][j] = 0
 * else:
 *      max{ dp[k][l] + 1 for k < i && s[k] < s[i] && l < j && t[l] < t[j] }
 *
 * 此时复杂度是 O(n^4)
 * 可以继续优化
 * dp[i][j] 考虑到s[i], t[j], 选择了t[j] 字符
 *
 * if s[i] != t[j]:
 *      dp[i][j] = dp[i-1][j]      // 存在前缀信息dp[i-1]里
 * else:
 *      max{ dp[i-1][l] + 1 for l < j && t[l] < t[j] }     (s[i] == t[j] 已经匹配了)
 *
 * 此时复杂度为 O(n^3)
 * 如果是严格递增序列，可以继续优化，如果非严格递增，则复杂度O(n^3)
 * 当外层循环 i, 内层循环 j时，总是 t[j] 和 s[i] 比较
 * 用一个mx变量维护max信息，找到 max(t[l]) < t[j] == s[i]
 * if t[j] < s[i]:
 *      dp[i][j] = dp[i-1][j]
 *      mx = Math.max(mx, dp[i-1][j])
 * else if t[j] == s[i]:
 *      dp[i][j] = mx + 1
 *
 * 此时复杂度为 O(n^2)
 */
public class LCIS {


    public int lcis_n3(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int max = 0;
                    for (int k = 1; k < j; k++) {
                        if (s2.charAt(k - 1) < s2.charAt(j - 1)) {
                            max = Math.max(max, dp[i - 1][k]);
                        }
                    }
                    dp[i][j] = max + 1;
                }
            }
        }
        int res = 0;
        for (int j = 1; j <= n; j++) {
            res = Math.max(res, dp[m][j]);
        }
        return res;
    }

    public int lcis_n2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s2.charAt(j  - 1) < s1.charAt(i - 1)) {
                    max = Math.max(max, dp[i - 1][j]);
                }
                if (s2.charAt(j - 1) == s1.charAt(i - 1)) {
                    dp[i][j] = max + 1;
                }
            }
        }
        int res = 0;
        for (int j = 1; j <= n; j++) {
            res = Math.max(res, dp[m][j]);
        }
        return res;
    }
}
