package Math.Catalan;

import Math.CombinationNumber.CombinationNumber;

/**
 * Catalan Number
 */
public class Catalan {
    int mod = (int)1e9 + 7;

    /**
     *  C(n) = C(0)*C(n-1) + C(1)*C(n-2) + ... + C(n-1)*C(0)     n >= 2
     *
     *  C(n) = Combine(2n, n) - Combine(2n, n-1)   Combine表示组合数
     *
     *  相关应用 https://blog.csdn.net/Morning_Glory_JR/article/details/102760802
     *
     *  括号化
     *
     *      矩阵连乘： P=a1×a2×a3×……×an，依据乘法结合律，不改变其顺序，只用括号表示成对的乘积，试问有几种括号化的方案？(C(n)种) [3]
     *
     * 出栈次序
     *
     *      一个栈(无穷大)的进栈序列为1，2，3，…，n，有多少个不同的出栈序列?
     *
     * 凸多边形三角划分
     *
     *      凸多边形划分为三角形，切割线不想交，共有多少种情况？
     *
     * 街道选择
     *
     *      一位大城市的律师在她住所以北n个街区和以东n个街区处工作。每天她走2n个街区去上班。如果她从不穿越（但可以碰到）从家到办公室的对角线，那么有多少条可能的道路？
     *
     * 不同的二叉搜索树的数目
     *
     *      给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。（能构成C(N)个）
     *
     * n对括号正确匹配数目
     *
     *      n对括号有多少种正确配对的可能
     */
    public long catalen(int n) {
        CombinationNumber cn = new CombinationNumber();
        return cn.combination(2 * n, n) - cn.combination(2 * n, n - 1);
    }

    public int catalan1(int n) {
        long[] c = new long[n + 1];
        c[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                c[i] += (c[j] * c[i - j - 1]) % mod;
            }
        }
        return (int)c[n];
    }

}
