package GameTheory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Impartial Game
 * 1. 两名玩家交替行动
 * 2. 在游戏任何时刻，可以进行的行动与玩家是谁无关
 * 3. 不能行动的玩家判负
 *
 * 有向图游戏
 * 给定一个有向无环图，和一个起点，两名玩家交替移动一个棋子，无法移动的人判负。
 * 任何公平组合游戏都可以转化为一个有向图游戏。把每一个局面看成一点，并从每个局面沿合法行动能够到达的下一个局面连有向边。
 *
 * 先手必胜状态(normal play) 与 先手必败状态(Misere Play)
 * 1. 无法操作的状态是 先手必败状态
 * 2. 至少有一种操作可以走到必败状态的为必胜状态
 * 3. 无论怎么操作都走不到必败状态的为必败状态
 */
public class Nim {
    int N = 105;
    int n;
    int[] stones = new int[N];

    /**
     * Nim 游戏
     * n 堆物品，每堆有 ni 个，两个玩家轮流取走任意一堆的任意个物品，但不能不取。
     * 拿走最后一个物品的玩家胜。(即不能操作的玩家败)
     * 例如有两堆石子，分别有 2，3 个
     * A 从第二堆里拿一个，还剩 2， 2 个
     * 之后不管 B 怎么拿，A 都做镜像操作，即在另一堆里拿相同个数的石子。
     * 最后一定是 B 先碰到两堆都是 0 个的情况。A 必胜
     *
     * 结论： a1 ^ a2 ^ a3 ^ ... ^ an = 0 则先手必败，否则先手必胜    （例如 1 ^ 2 ^ 3 = 0 )
     *
     * 1. 0 ^ 0 ^ 0 ... ^ 0 = 0
     * 2. a1 ^ a2 ^ a3 ... ^ an = x != 0
     *    x 的二进制表示里，最高的一位 1 在第 k 位，则 a1 到 an 中必然存在一位数 ai 第 k 位是 1
     *    ai ^ x < ai
     *    我们可以从 ai 里拿走 ai-(ai^x) 堆，ai 还剩 ai^x，所有的异或值变为 0
     *    所以 a1 ^ a2 ^ ... a^n = x != 0 可以经过操作变为 0
     * 3. a1 ^ a2 ^ a3 ^ ... a^n = 0
     *    不管我们怎么拿，都不会还等于 0
     *    反正法： a1 ^ a2 ^ aii ^ ... ^ an = 0   两式左右相乘: ai ^ aii = 0 则 ai == aii 矛盾
     */
    public boolean nim(int n) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= stones[i];
        }
        return res != 0;             // 不为 0 必胜
    }

    /**
     * 台阶 Nim
     * 有一个n级台阶的楼梯，每级台阶上都有若干个石子
     * 两位玩家轮流操作，每次操作可以从任意一级台阶上拿若干个石子放到下一级台阶中（不能不拿）。
     * 已经拿到地面上的石子不能再拿，最后无法进行操作的人视为失败。
     *
     * 例如 3 个台阶上分别有 2， 1， 3 个
     * A 先从 3 台阶拿 1 个到 2 台阶，变成 2, 2, 2 个
     * 之后如果 B 从 2 台阶拿多少，我们都从 1 台阶拿多少放到地面。（保证 1， 3 台阶石子数目不变）
     *     如果 B 从 1 台阶拿，我们则可以从 1，3 台阶拿若干使得 a1 ^ a3 = 0
     * 则最后一定是 B 遇到 1，3 台阶都是 0 的情况，必败
     *
     * 结论：a1 ^ a3 ^ a5 ^...^ a2k-1 = 0 则先手必败，否则先手必胜   （与偶数台阶上的石子无关）
     *
     * 如果 B 从偶数台阶拿 n 石子，A 则可以在其下一层拿 n 石子 (保证奇数台阶石子不变）
     * 如果 B 从奇数台阶拿，则 a1 ^ a3 ^ a5 ^...^ a2k-1 != 0, 我们可以必可以操作使异或积重新变成 0
     */
    public boolean odd_nim(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                res ^= stones[i];
            }
        }
        return res != 0;         // 不为 0 必胜
    }

    /**
     *  Sprague–Grundy Theorem
     *  定义 mex 函数的值为不属于集合 S 中的最小非负整数
     *  对于状态 x 和它的所有 k 个后继状态 y1,y2,...,yk
     *  SG(x) = mex(SG(y1), SG(y2), ..., SG(yk))
     *  SG(终点) = 0
     *
     *  结论：若 SG(x) = 0, 先手必败，否则先手必胜。
     *  因为如果 SG(x) != 0, 则后继状态中一定有 SG(yi) = 0, 则转移到 yi
     *      如果 SG(x) = 0, 则后继状态一定没有 0 的。
     *
     *  如果玩家有很多有向图游戏，玩家可以选择在任何一个图中走一步，如果任何一个图都操作不了，则必败
     *
     *  结论：
     *  如果 SG(s1) ^ SG(s2) ^ SG(s3) ^ ... ^ SG(sn) = 0 则先手必败，否则必胜
     *
     *  如果 SG(s1) ^ SG(s2) ^...^ SG(sn) = x != 0,
     *  则可以找到一个 SG(si) ^ x 的局面，并转移过去。 (因为 SG(si) ^ x < SG(si)， 则我可以在 SG(si) 堆里面取走 SG(si) - SG(si) ^ x 个石子)
     *
     */

    /**
     * 集合 Nim
     * 给定 n 堆石子以及一个由 k 个不同正整数构成的数字集合 S。
     * 现在有两位玩家轮流操作，每次操作可以从任意一堆石子中拿取石子，每次拿取的石子数量必须包含于集合S，最后无法进行操作的人视为失败。
     *
     * 我们可以把 n 的石子看成 n 个有向图游戏，有 n 个 SG(si) 值。
     * 判断 SG(s1) ^ SG(s2) ^ ... ^ SG(sn) == 0, 如果为 0 必败，否则必胜
     */
    int K = 105;       // 可选择集 set 大小 K
    int M = 100005;    // 石子个数上限 即为 SG 的最大个数
    int k;
    int[] set = new int[K];
    int[] dp = new int[M];    // 记录 某石子个数下 SG 的值

    public boolean set_nim(int n, int k) {
        this.k = k;

        Arrays.fill(dp, -1);            // initialize

        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= sg(stones[i]);                       // 所有初始点的 sg 值的异或积
        }
        return res != 0;
    }

    private int sg(int x) {
        if (dp[x] != -1) {
            return dp[x];
        }
        Set<Integer> state = new HashSet<>();
        for (int i = 0; i < k; i++) {
            if (x >= set[i]) {
                state.add(sg(x - set[i]));     // 添加所有后继状态的 sg 值
            }
        }
        for (int i = 0; ; i++) {
            if (!state.contains(i)) {            // 为当前状态 x 寻找 sg 值
                dp[x] = i;
                return i;
            }
        }
    }

    /**
     *  拆分 Nim
     *  给定n堆石子，两位玩家轮流操作，每次操作可以取走其中的一堆石子，然后放入两堆规模更小的石子
     *  新堆规模可以为0，且两个新堆的石子总数可以大于取走的那堆石子数，最后无法进行操作的人视为失败。
     *  例如 我们可以拿走有 10 个的一堆，然后放入两堆石子，分别有 8 个 和 5 个。
     *  因为我们放入的每堆石子数是逐渐减小的，所以该博弈一定会结束。
     *
     *  一堆石子进行操作后会变成两堆 s -> (a1,a2) 或 (b1,b2) 或 (c1,c2)
     *  SG(b1,b2) = SG(b1) ^ SG(b2)
     */
    public boolean partition_nim(int n) {

        Arrays.fill(dp, -1);             // initialize

        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= sg1(stones[i]);
        }
        return res != 0;
    }
    private int sg1(int x) {
        if (dp[x] != -1) {
            return dp[x];
        }
        Set<Integer> state = new HashSet<>();
        for (int i = 0; i < x; i++) {           // i 堆 必须比 x 小
            for (int j = 0; j <= i; j++) {
                state.add(sg(i) ^ sg(j));       // 两堆石子的异或
            }
        }
        for (int i = 0; ; i++) {
            if (!state.contains(i)) {
                dp[x] = i;
                return i;
            }
        }
    }

}
