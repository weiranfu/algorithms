package DP.Knapsack;

import java.util.Scanner;

/**
 * 每个物品体积是vi, 重量是mi, 价值是wi
 * 要求体积，重量满足条件时的最大价值
 *
 * dp[i][j] 体积是 i，重量是 j 时的最大价值
 */
public class Knapsack2D {

    Scanner sc = new Scanner(System.in);
    int N, V, M;
    int maxv = 105;
    int[][] dp = new int[maxv][maxv];

    void run() {
        N = sc.nextInt(); V = sc.nextInt(); M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int v = sc.nextInt(), m = sc.nextInt(), w = sc.nextInt();
            for (int j = V; j >= v; j--) {
                for (int k = M; k >= m; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - v][k - m] + w);
                }
            }
        }
        System.out.println(dp[V][M]);
    }
}
