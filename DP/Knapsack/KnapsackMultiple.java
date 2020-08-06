package DP.Knapsack;

import java.util.Scanner;

/**
 * 混合背包
 */
public class KnapsackMultiple {
    Scanner sc = new Scanner(System.in);
    int maxn = 1005;
    int N, V;
    int[] dp = new int[maxn];

    void run() {
        N = sc.nextInt(); V = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int v = sc.nextInt(), w = sc.nextInt(), s = sc.nextInt();
            if (s == -1) {
                zero_one(v, w);
            } else if (s == 0) {
                unbounded(v, w);
            } else {
                bounded(v, w, s);
            }
        }

        System.out.println(dp[V]);
    }

    void zero_one(int v, int w) {
        for (int i = V; i >= v; i--) {
            dp[i] = Math.max(dp[i], dp[i - v] + w);
        }
    }

    void unbounded(int v, int w) {
        for (int i = v; i <= V; i++) {
            dp[i] = Math.max(dp[i], dp[i - v] + w);
        }
    }

    void bounded(int v, int w, int c) {
        for (int k = 1; k <= c; k *= 2) {
            c -= k;
            zero_one(k * v, k * w);
        }
        zero_one(c * v, c * w);
    }
}
