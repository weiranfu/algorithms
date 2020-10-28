package DP.LCS_LIS;

import java.util.Arrays;

/**
    Count the number of longest increasing subsequences
 */
public class CountLIS {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];  // length of LPS
        int[] cnt = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);
        int max = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {   // if we find longer one
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];      // update cnt
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];     // collect cnt
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                res = cnt[i];
            } else if (dp[i] == max) {
                res += cnt[i];
            }
        }
        return res;
    }
}
