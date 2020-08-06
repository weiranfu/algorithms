package DP.Count_Digit;

/**
 * 我们用一个升序集合里面的数字(1-9)（可以多次使用）来组成多位数，求可以组成多少不比 N 大的正整数。
 * 例如set D = {"1", "3", "5"}, N = 40
 * 可以组成 1, 3, 5, 11, 13, 15, 31, 33, 35
 *
 * 以D = {1， 3， 5， 7}， N=5371为例
 * 0. 首先比5371位数小的都满足 xxx, xx, x，                 总共有：4*4*4 + 4*4 + 4个
 * 与5371位数相同的:
 * 1. 先看首位，小于5的肯定满足：                           总共有：2*4*4*4个
 *    3XXX
 *    1XXX
 * 2. 等于5的情况，再看下一位，小于3的肯定满足：              总共有：1*4*4个
 *    51XX
 * 3. 等于3的情况，看下一位，小于7的肯定满足：                总共有：3*4个
 *    535X
 *    533X
 *    531X
 * 4. 等于7的情况，看下一位，因为是最后一位了，因此小于等于1的都能满足：总共有： 1个
 *    5371
 *
 * 可以看出：主线的算法应该是从N的最高位到最低位遍历下去，存在以下几种情况：
 *    1. D中所有数字都大于当前位，说明当前位没有任何可能的取值，那么遍历结束，返回当前res
 *    2. D从某个数字d开始小于当前位，说明从d开始比它小的数都是当前位可以取到的值，那么遍历结束，返回当前res
 *    3. D从某个数字d开始等于当前位，说明比d小的数都是满足条件的，
 *       但是d是否满足还得看N的下一位，那么跳到N的下一位，继续与D中数据比较；
 *
 *  如果完成了整个N的遍历，说明最后一次比较是等于的情况，但是我们在3中并没有包含这一情况，因此结果还要加 1  ！！！！
 *
 * */
public class CountNum {

    public int atMostNGivenDigitSet(String[] D, int N) {
        int n = D.length;
        String s = Integer.toString(N);
        int len = s.length();
        int res = 0;
        for (int i = 1; i < len; i++) {             // 比N位数小的，都是满足条件的
            res += (int)Math.pow(n, i);
        }
        // 考虑与N位数相同的情况
        for (int i = 0; i < len; i++) {             // from most significant digit
            char c = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {      // check each digit
                char d = D[j].charAt(0);
                if (d < c) {                        // 如果 d < c, 说明不用再比下去了，当前位的可能数量为小于等于d的数量
                    res += (j + 1) * (int)Math.pow(n, len - i - 1);
                    return res;
                } else if (d > c) {
                    if (j == 0) {                   //如果j到0了，说明D中所有的数都比N当前位置的数大，当前位可能的数量为0，此时无需再继续往下比
                        return res;
                    }
                } else {                            // d == c, 则继续比较C的下一位，当前位的可能数量为小于d的数量（不包括d）
                    res += j * (int)Math.pow(n, len - i - 1);
                    break;
                }
            }
        }
        return res + 1;
    }
}
