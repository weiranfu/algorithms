package Graph.ShortestPath.Floyd;

import Graph.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Floyd can solve the all-pairs shortest paths problem in edge-weighted digraphs with no negative cycles.
 *
 * floyd算法是基于动态规划的
 * d[k, i, j] 表示 从i出发，只经过 1~k 中某一点 到达j点的最短距离 ( k 可以是 i 或 j)
 *
 * This class finds either a shortest path between every pair of vertices.
 * The idea is to compute shortest path between v and w using only 0, 1, ..., V as intermediate vertices
 * The edge weights can be positive, negative, or zero.
 * The time complexity is O(V^3)
 */

public class Floyd {
    int INF = 0x3f3f3f3f;
    int[][] dist;         // distTo[v][w] = length of shortest v->w path

    public Floyd(int[][] edges, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for (int k =  0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    public boolean hasPath(int s, int t) {
        return dist[s][t] < INF / 2;                // 注意如果有负边的话，dist[v] < INF 是可能的
    }

    public double dist(int s, int t) {
        return dist[s][t];
    }
}
