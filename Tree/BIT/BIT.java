package Tree.BIT;

/**
 * Binary Index Tree is used to query sum of values between two indices in an array.
 * Binary Index Tree can query prefixSum of values in an array efficiently.
 * first node is at 1 index, not 0 index. 0 index is filled with a start node -1.  BIT is 1 based.
 * [-1, 1, 2, 3, 4, 5]
 * prefixSum(5) = 1 + 2 + 3 + 4 + 5 = 15
 * prefixSum(2) = 1 + 2 = 3
 * sum(3, 5) = prefix(5) - prefix(2) = 12
 *
 * Link: https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
 */
public class BIT {
    int n;
    int[] sum;

    public BIT(int[] a) {
        n = a.length;
        sum = new int[n + 1];            // BIT is 1 based.
        for (int i = 0; i < n; i++) {
            update(i + 1, a[i]);   // move 1 step right
        }
    }

    private void update(int index, int delta) {
        for (int i = index; i <= n; i += lowbit(i)) {
            sum[i] += delta;
        }
    }

    private int query(int index) {
        int res = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            res += sum[i];
        }
        return res;
    }

    /*************  User API  ****************/

    public void alter(int index, int delta) {
        update(index + 1, delta);
    }

    public int sum(int start, int end) {
        return query(end + 1) - query(start);    // move one step right
    }

    /* returns a lowbit value of x */
    private int lowbit(int x) {
        return x & (-x);
    }
}