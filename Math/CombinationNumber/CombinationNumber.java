package Math.CombinationNumber;

/**
 * 组合数 C(a,b)  从不同的 a 个元素里面选 b 个元素 = a!/((a-b)!*b!)
 */
public class CombinationNumber {
    int N = 1000;
    long[][] c = new long[N][N];
    int mod = (int)1e9 + 7;

    /** O(n^2)
     * 递推：杨辉三角
     * C(a,b) = C(a-1,b-1) + C(a-1,b)      (包含一个特殊元素 + 不包含)
     * 预处理  C(a,b)
     */
    public long combination(int a, int b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) c[i][j] = 1;               // 初始化
                else c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod;
            }
        }
        return c[a][b];
    }
    public int combine(int a, int b) {
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 0; i <= a; i++) {
            for (int j = 1; j <= i; j++) {
                dp[j] = (dp[j - 1] + dp[j]) % mod;
            }
        }
        return dp[b];
    }

    /** O(nlogn)
     *  C(a,b) = a!/((a-b)!*b!)
     *  预处理阶乘
     *  fact[i] = i! % p
     *  infact[i] = (i!)^-1 % p    表示 i! 的逆元模 p
     *
     *  C(a,b) = fact[a] * infact[b-a] * infact[b] % p
     */
    long[] fact = new long[N];
    long[] infact = new long[N];

    public int combination2(int a, int b) {
        fact[0] = 1;
        infact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = fact[i - 1] * i % mod;
            infact[i] = infact[i - 1] * quickPow(i, mod - 2, mod) % mod;
        }
        return (int)(fact[a] * infact[a-b] % mod * infact[b] % mod);
    }
    long quickPow(long a, long b, long p) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = (res * a) % p;
            }
            a = (a * a) % p;
            b >>= 1;
        }
        return res;
    }
}
