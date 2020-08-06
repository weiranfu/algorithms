package Tree.BIT;

/**
 * Binary Index Tree in 2D.
 * x and y are 1 based,
 * so we need to move them one step right and down.
 */
public class BIT_2D {

    int m, n;
    int[][] sum;
    int[][] nums;

    public BIT_2D(int[][] nums) {
        this.nums = nums;
        m = nums.length;
        n = nums[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                alter(i, j, nums[i][j]);
            }
        }
    }

    private void update(int x, int y, int delta) {
        for (; x <= m; x += lowbit(x)) {
            for (int j = y; j <= n; j += lowbit(j)) {
                sum[x][j] += delta;
            }
        }
    }

    private int query(int x, int y) {
        int res = 0;
        for (; x > 0; x -= lowbit(x)) {
            for (int j = y; j > 0; j -= lowbit(j)) {
                res += sum[x][j];
            }
        }
        return res;
    }

    /*************  User API  ****************/

    public void alter(int x, int y, int delta) {
        update(x + 1, y + 1, delta);
    }

    public int rangeSum(int x1, int y1, int x2, int y2) {
        return query(x2 + 1, y2 + 1) - query(x2 + 1, y1) - query(x1, y2 + 1) + query(x1, y1);
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}
