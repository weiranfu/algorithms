package DP.TSP;

public class BitOperation {

    /**
     * 在状态压缩中，我们要用的一些位运算来进行集合操作
     */
    public void bit(int A, int B, int c) {

        A |= 1 << c;        // insert c
        A &= ~(1 << c);     // remove c
        A ^= 1 << c;        // remove c if (A>>c)&1 == 1
        A = A & (-A);       // low bit of A

        A = 0;              // empty set
        A = A | B;          // union
        A = A & B;          // intersection

        int size = 15;
        int ALL = (1 << size) - 1;  // universal set
        A = ALL ^ A;                // complementary set of A
        boolean t = (A & B) == B;   // B is subset of A

        // enumerate all subsets of ALL
        for (int i = 0; i <= ALL; i++) {
            ;
        }

        // enumerate all subsets of A (不包括空集)
        for (int i = A; i > 0;) {
            // do something
            i = (i - 1) & A;        // 每次让 i 减 1， 但是要保证是 A 的子集，所以  & A
        }

        // enumerate all subsets of A (包括空集)
        int subset = A;
        do {
            // do something
            subset = (subset - 1) & A;
        } while (subset != A);


        // count the number of element in A
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if ((A & (1 << i)) != 0) cnt++;
        }
        // or
        for (int i = A; i > 0; i >>= 1) {
            cnt += i & 1;
        }
        // or
        cnt = 0;
        while (A > 0) {
            cnt++;
            A = A & (A - 1);
        }
        // or
        cnt = Integer.bitCount(A);


        // 求 ALL 的各个子集的 元素个数
        int[] cnts = new int[1 << size];
        for (int i = 0; i < (1 << size); i++) {
            cnts[i] = cnts[i >> 1] + i & 1;
        }
    }

    public int lowbit(int x) {
        return x & (-x);
    }

    public int highbit(int x) {
        int p = lowbit(x);
        while (p != x) {
            x -= p;
            p = lowbit(x);
        }
        return p;
    }

    public boolean isPowerOf2(int x) {
        return x != 0 && (x & (x - 1)) != 0;
    }

    public String toBinary(int x) {
        return Integer.toBinaryString(x);
    }
}
