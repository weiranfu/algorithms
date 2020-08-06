package DP.Fibonacci;

/**
 * Tribonacci number:
 * T(n+3) = T(n+2) + T(n+1) + T(n)
 * T(0) = 0, T(1) = 1, T(2) = 1
 */
public class Tribonacci {

    /**
     * 采用滚动数组，取模计算index
     */
    public int tribonacci(int n) {
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i % 3] = dp[(i + 1) % 3] + dp[(i + 2) % 3] + dp[i % 3];
        }
        return dp[n % 3];
    }
}
