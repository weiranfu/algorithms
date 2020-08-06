package Greedy.MergeFruit;

import java.util.PriorityQueue;

/**
 * 合并果子
 * 有 n 堆果子，我们每次可以选择两堆果子进行合并，耗费体力是两堆果子重量之和。
 * 如果将其合并成一堆，求最小耗费的体力。
 *
 * Huffman Tree
 * 每个合并的节点都是完全二叉树上的一点。每一点耗费的体力是重量乘上该点到 root 的距离。
 *
 * 算法原理：
 * 我们每次从所有堆中挑选最轻的两堆进行合并
 */
public class MergeFruit {

    public int minCost(int[] fruits) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int fruit : fruits) {
            pq.add(fruit);
        }

        int cost = 0;
        while (pq.size() > 1) {     // if there're two piles of fruits
            int a = pq.poll();
            int b = pq.poll();
            cost += a + b;
            pq.add(a + b);
        }

        return cost;
    }
}
