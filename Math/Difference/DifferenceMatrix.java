package Math.Difference;

/**
 * 差分矩阵(二维差分)
 *
 * A[i][j] = B[0][0] + B[0][1] + B[1][0] +...+ B[i][j]  (所有被包括的 B 的前缀和)
 *
 * 应用：给一个子矩阵全部加 c
 *     (x1,y1)-------—
 *     ｜            ｜
 *     ｜            ｜
 *     ｜            ｜
 *     ｜            ｜
 *     ｜_________(x2,y2)
 *
 *  B[x1][y1] += c, B[x2+1][y1] -= c, B[x1][y2+1] -= c, B[x2+1][y2+1] += c
 */
public class DifferenceMatrix {

    int[][] A, B;

    public int[][] getDifferenceMatrix(int[][] matrix, int[][] operations) {
        int m = matrix.length, n = matrix[0].length;
        A = new int[m + 2][n + 2];
        B = new int[m + 2][n + 2];                  // 1 based array and 我们可能对 B[x2+1][y2+1] += c 进行操作

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                A[i][j] = matrix[i - 1][j - 1];
                add(i, j, i, j, A[i][j]);           // 想象成原数组 A 是空的，然后在 (i,j)-(i,j) 的单位矩阵上加上 A[i][j]
            }
        }

        for (int[] op : operations) {
            int x1 = op[0], y1 = op[1], x2 = op[2], y2 = op[3], c = op[4];
            add(x1, y1, x2, y2, c);
        }

        for (int i = 1; i <= m; i++) {              // 最后对 B 求前缀和得到修改后的 A
            for (int j = 1; j <= n; j++) {
                B[i][j] += B[i - 1][j] + B[i][j - 1] - B[i - 1][j - 1];
            }
        }
        return B;
    }

    private void add(int x1, int y1, int x2, int y2, int c) {
        B[x1][y1] += c;
        B[x2 + 1][y1] -= c;
        B[x1][y2 + 1] -= c;
        B[x2 + 1][y2 + 1] += c;
    }
}
