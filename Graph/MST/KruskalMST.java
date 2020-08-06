package Graph.MST;

import Graph.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/** Get the Minimum Spanning Tree(MST) using Kruskal's algorithm
 * in an edge-weighted graph. The edge weights can be positive,
 * zero, or negative and need not be distinct.
 * Kruskal's algorithm: Every time we add the lightest edge to MST,
 * if the edge is not cutting edge, then continue.
 * We don't need to sort edges, just put then in a priority queue.
 * Using UnionFind data structure to determine whether two vertices are connected.
 * 稀疏图用 Kruskal算法，稠密图用朴素版Prim算法，堆优化版Prim算法一般不会用
 * O(ElogE) (排序)
 */
public class KruskalMST {
    int n, m;
    List<int[]> mst;
    int weight;
    int cnt;
    int[] uf;

    /* edges: int[]{ u, v, w } */
    public KruskalMST(int n, int[][] edges) {
        this.n = n;
        m = edges.length;
        uf = new int[n];
        for (int i = 0; i < n; i++) {                   // initialize
            uf[i] = i;
        }

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);      // sort edges

        weight = 0;
        cnt = 0;
        for (int i = 0; i < m; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            int a = find(u), b = find(v);
            if (a != b) {                               // not connected yet
                uf[a] = b;
                weight += w;
                cnt++;
                mst.add(edges[i]);
            }
            if (cnt == n - 1) break;
        }

        if (cnt < n - 1) {
            System.out.println("No MST!!!");
        }
    }

    private int find(int x) {
        if (x != uf[x]) {
            uf[x] = find(uf[x]);
        }
        return uf[x];
    }

    public double weight() {
        return weight;
    }

    public Iterable<int[]> edges() {
        return mst;
    }
}
