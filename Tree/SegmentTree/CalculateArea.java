package Tree.SegmentTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Line Sweep
 * We are given a list of (axis-aligned) rectangles.
 * Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner,
 * and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.
 *
 * Find the total area covered by all rectangles in the plane. https://leetcode.com/problems/rectangle-area-ii/
 *
 * 注意表示区间长度要用delta distance， [l, r] 的长度是 pos[r + 1] - pos[l]
 * 所以插入一个新区间 [l, r], 表示为插入 update(l, r - 1)，注意是 r - 1 以避免越界
 */
public class CalculateArea {
    class Solution {
        class Node {
            int cover; // cover times
            int coverL;// cover length
        }

        int N;
        List<Integer> pos;
        Node[] tree;
        int mod = (int)1e9 + 7;

        public int rectangleArea(int[][] rectangles) {
            List<int[]> lines = new ArrayList<>();
            pos = new ArrayList<>();
            for (int[] rectangle : rectangles) {
                int x1 = rectangle[0], y1 = rectangle[1];
                int x2 = rectangle[2], y2 = rectangle[3];
                int[] line1 = new int[]{x1, y1, y2, 1};
                int[] line2 = new int[]{x2, y1, y2, 0};
                lines.add(line1);
                lines.add(line2);
                pos.add(y1);
                pos.add(y2);
            }
            pos = new ArrayList<>(new HashSet<>(pos));
            Collections.sort(pos);
            N = pos.size();

            tree = new Node[N << 2];
            for (int i = 0; i < tree.length; i++) tree[i] = new Node();

            Collections.sort(lines, (a, b) -> a[0] - b[0]);
            long area = 0;
            int preX = -1;
            int i = 0, n = lines.size();
            while (i < n) {
                int X = lines.get(i)[0];
                if (preX != -1) area = (area + (long)tree[1].coverL * (X - preX)) % mod;
                preX = X;
                while (i < n && lines.get(i)[0] == X) {
                    int[] line = lines.get(i++);
                    int y1 = line[1], y2 = line[2], in = line[3];
                    int Y1 = find(y1), Y2 = find(y2);
                    if (in == 1) {
                        update(Y1, Y2 - 1, 1, N, 1, 1);   // index is [Y1, Y2 - 1]
                    } else {
                        update(Y1, Y2 - 1, 1, N, 1, -1);
                    }
                }
            }
            return (int)area;
        }

        private void pushUp(int l, int r, int rt) {
            if (tree[rt].cover > 0) {
                tree[rt].coverL = pos.get(r) - pos.get(l - 1);       // delta distance from l to r + 1
            } else {
                if (l == r) tree[rt].coverL = 0;
                else tree[rt].coverL = tree[rt * 2].coverL + tree[rt * 2 + 1].coverL;
            }
        }

        private void update(int L, int R, int l, int r, int rt, int c) {
            if (L <= l && r <= R) {
                tree[rt].cover += c;
                pushUp(l, r, rt);
                return;
            }
            int mid = l + (r - l) / 2;
            if (mid >= L) update(L, R, l, mid, rt * 2, c);
            if (mid < R) update(L, R, mid + 1, r, rt * 2 + 1, c);
            pushUp(l, r, rt);
        }

        private int find(int x) {
            int l = 0, r = N - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (pos.get(mid) == x) return mid + 1;
                else if (pos.get(mid) < x) l = mid + 1;
                else r = mid - 1;
            }
            return -1;
        }
    }
}
