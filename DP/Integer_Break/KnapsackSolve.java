package DP.Integer_Break;

/**
 * 一个正整数n可以表示成若干个正整数之和，形如：n=n1+n2+…+nk，其中n1≥n2≥…≥nk,k≥1。
 * 我们将这样的一种表示称为正整数n的一种划分。
 * 现在给定一个正整数n，请你求出n共有多少种不同的划分方法。
 */
public class KnapsackSolve {

    int maxn = 1005;
    int[] dp = new int[maxn];
    int[][] dp2 = new int[maxn][maxn];
    int n;

    /**
     * 背包解法
     * 背包容量是n，物品是体积是 1,..,n, 价值也是1,...,n
     * 每一种 "恰好" 装满背包的选法正好就是一种整数划分方法
     * f[i][j]  表示前i个整数（1,2…,i）"恰好" 拼成j的方案数
     * 求方案数：把集合选0个i，1个i，2个i，…全部加起来
     *
     * f[i][j] =  f[i - 1][j] + f[i - 1][j - i] + f[i - 1][j - 2 * i] + ...;
     * f[i][j - i] =            f[i - 1][j - i] + f[i - 1][j - 2 * i] + ...;
     *
     * 因此 f[i][j] = f[i−1][j] + f[i][j−i]
     * 即   f[j] = f[j] + f[j - i]         从左到右update   i 表示第 i 个物品
     */
    public int int_break2D(int n) {
        this.n = n;

        dp2[0][0] = 1;                     // initialize

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j >= i) dp2[i][j] = dp2[i - 1][j] + dp2[i][j - i];
                else dp2[i][j] = dp2[i - 1][j];
            }
        }
        return dp2[n][n];
    }

    public int int_break(int n) {
        this.n = n;

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = dp[j] + dp[j - i];
            }
        }
        return dp[n];
    }
}
