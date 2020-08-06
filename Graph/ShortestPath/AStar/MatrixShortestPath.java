package Graph.ShortestPath.AStar;

import java.util.PriorityQueue;

/**
 * There is a matrix n x n. And we can move in 8 directions.
 * 可以走8个方向的话，两个点的距离则为 切比雪夫距离
 * Chebyshev Distance = Math.max(Math.abs(x1-x2), Math.abs(y1-y2))
 *
 * If grid[i][j] == 1, there is a stone so that we cannot move to it.
 * Find the shortest path from (0,0) to (m-1,n-1).
 */
public class MatrixShortestPath {
    class Node implements Comparable<Node> {
        int x, y, f;
        Node(int x, int y, int f) {
            this.x = x; this.y = y; this.f = f;
        }
        @Override
        public int compareTo(Node n) {
            return this.f - n.f;
        }
    }
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
    int m, n;

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        m = grid.length;
        n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;
        if (n == 1) return 1;                           // corner case
        int[][] dist = new int[m][n];
        int[][] visited = new int[m][n];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist[0][0] = 1;
        queue.add(new Node(0, 0, h(0, 0)));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int i = node.x, j = node.y;
            if (visited[i][j] == 2) continue;                       // avoid duplicate nodes
            visited[i][j] = 2;
            if (i ==  m - 1 && j == n - 1) return dist[i][j];       // find end
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (grid[x][y] == 1 || visited[x][y] == 2) continue; // blocked or visited
                if (visited[x][y] == 0 || dist[x][y] > dist[i][j] + 1) { // update cost
                    dist[x][y] = dist[i][j] + 1;
                    visited[x][y] = 1;
                    queue.add(new Node(x, y, dist[x][y] + h(x, y)));
                }
            }
        }
        return -1;  // cannot reach end
    }

    /**
     * Chebyshev Distance = Math.max(Math.abs(x1-x2), Math.abs(y1-y2))
     */
    private int h(int x, int y) {
        return Math.max(m - 1 - x, n - 1 - y); // Chebyshev Distance
    }
}
