package DP.TSP;

import java.util.ArrayList;
import java.util.List;

/**
 * 求把 N*M 的棋盘分割成若干个1*2的的长方形，有多少种方案。
 * 例如当N=2，M=3时，共有3种方案。
 *  ---------       ----------      -----------
 * ｜____｜  ｜     ｜   ｜____｜    ｜  ｜  ｜  ｜
 * ｜____｜__｜     ｜___｜____｜    ｜__｜__｜__｜
 *
 * 首先可以看到 N, M 的范围都很小(<15)， 可能的求解思路就是  状态压缩DP
 * 对于某一列，我们只需要填充 1x2的格子多个, 然后剩下的位置肯定可以由 2x1的格子
 * 也就是我们只需要考虑 2x1的格子在某一列中是怎么占据的就可以了。
 * 即连续的空位必须是偶数，那么我们必然可以填充 2x1 的格子。
 * 现在我们定义 dp[i][s] 表示第 i 列 的 s 状态可以表示的方案数 (s为有多少个 1x2 的格子从 i-1 列伸过来，伸过来为1，否则为 0)
 *
 * dp[i][k] += dp[i-1][s]
 * 从第i-1列的s状态转移到第i列的k状态
 * s 与 k 不能重合: (s & k) == 0
 * k 中连续的 0 的个数是偶数
 *
 * 最后返回第 m 列状态为 0 (没有格子伸到第m列，即正好放满）的方案数 dp[m][0]
 */
public class Mondrian_dream {
    int N = 12;
    int M = 1 << N;
    int n, m;
    boolean[] valid = new boolean[M];     // check state s is vaild
    List<Integer>[] state = new List[M];  // save all possible state s can transit to
    long[][] dp = new long[N][M];

    public long mondrian(int n, int m) {
        for (int i = 0; i < 1 << n; i++) {
            valid[i] = false;
            int cnt = 0;
            boolean isValid = true;
            for (int j = 0; j < n; j++) {
                if (((i>>j) & 1) == 0) {       // cnt 0
                    cnt++;
                } else {
                    if (cnt % 2 != 0) {        // number of 0 is even
                        isValid = false;
                        break;
                    }
                    cnt = 0;
                }
            }
            if (cnt % 2 != 0) {
                isValid = false;
            }
            valid[i] = isValid;
        }

        for (int i = 0; i < 1 << n; i++) {
            state[i] = new ArrayList<>();
            for (int j = 0; j < 1 << n; j++) {
                if ((i & j) == 0 && valid[j | i]) {  // i and j isn't overlapping
                    state[i].add(j);                 // i | j is a valid state
                }
            }
        }


        dp[0][0] = 1;                             // initialize

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                for (int k : state[j]) {
                    dp[i][j] += dp[i - 1][k];    // transit from k to j
                }
            }
        }
        return dp[m][0];
    }
}
