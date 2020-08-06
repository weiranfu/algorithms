package Math.QuickPower;

/**
 * 快速幂
 * a^n % mod
 */
public class QuickPower {

//    int mod = (int)1e9 + 7;

    /**
     * 二进制分解
     * 57 = 111001
     * 我们取对应二进制位为 1 的幂
     * a^57 = a^32 * a^16 * a^8 * a^1
     *
     * 补充模下乘法有：(a * b) % mod = ((a % mod) * (b % mod)) % mod;
     * 乘法有可能会overflow，应该用long
     */
    public int quickPower(int a, int n, int p) {
        long base = a;
        long res = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                res = (res * base) % p;
            }
            base = (base * base) % p;
            n >>= 1;
        }
        return (int)res;
    }


    /**
     * 求除法的模可以转化成逆元的乘积的模，即把除法变成乘法
     * a/b % p = a * x % p
     * => b * x % p = 1 % p
     * => b^(p-1) % p = 1 % p  (费马定理)
     * => x = b^(p-2) % p   (求得逆元)
     *
     * a/b % p = a * x % p = a * b^(p-2) % p
     */
    public int reversePower(int a, int p) {
        return quickPower(a, p - 2, p);
    }

}
