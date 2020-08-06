package Graph.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a matrix n x n. And we can move in 8 directions.
 * 可以走8个方向的话，两个点的距离则为 切比雪夫距离
 *
 * If grid[i][j] == 1, there is a stone so that we cannot move to it.
 * Find the shortest path from (0,0) to (m-1,n-1).
 */
public class MatrixShortestPath {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};

    public int shortestPathMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        dist[0][0] = 0;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int i = info[0], j = info[1];
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (dist[x][y] != -1) continue;                  // has visited
                if (grid[x][y] == 1) continue;                   // meets obstacle
                dist[x][y] = dist[i][j] + 1;
                if (x == m - 1 && y == n - 1) return dist[x][y]; // find end
                queue.add(new int[]{x, y});
            }
        }
        return dist[m - 1][n - 1];
    }
}
