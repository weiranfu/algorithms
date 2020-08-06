package Tree.SegmentTree;

/**
 * 权值线段树
 * 当值域很大时，要离散化
 * 支持查询 第K小值，某个值的rank，某个值的前驱和后继（floorKey, ceilingKey)
 * 相对于平衡树（AVL等）优势在于 代码简单，速度快
 */
public class WeightSegTree {
    class Node {
        int sum;       // sum of value
        int cnt;       // the number of times it shows up
    }

    int maxn = 10000;
    Node[] tree;

    public WeightSegTree() {
        tree = new Node[maxn << 2];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }
    }

    private void pushUp(int rt) {
        tree[rt].cnt = tree[rt * 2].cnt + tree[rt * 2 + 1].cnt;
        tree[rt].sum = tree[rt * 2].sum + tree[rt * 2 + 1].sum;
    }

    /**
     * Add / remove a value into WST
     * @param add true means add, false means remove
     */
    public void update(int value, boolean add, int l, int r, int rt) {
        if (l == r) {
            if (add) {
                tree[rt].cnt++;
                tree[rt].sum += value;
            } else {
                tree[rt].cnt--;
                tree[rt].sum -= value;
            }
            return;
        }
        int mid = l + (r - l) / 2;
        if (value <= mid) {
            update(value, add, l, mid, rt * 2);
        } else {
            update(value, add, mid + 1, r, rt * 2 + 1);
        }

        pushUp(rt);
    }

    /**
     * Find the Kth smallest num in all values. TopK smallest.
     */
    public int findKthSmallest(int K, int l, int r, int rt) {
        if (l == r) {
            return l;  // find the item
        }

        int mid = l + (r - l) / 2;
        if (tree[rt * 2 + 1].cnt >= K) {                     // left interval has more than K nums.
            return findKthSmallest(K, l, mid, rt * 2);
        } else {
            return findKthSmallest(K - tree[rt * 2].cnt, mid + 1, r, rt * 2 + 1);
        }
    }

    /**
     * Find the sum of top K num in all value.
     */
    public int sumTopK(int K, int l, int r, int rt) {
        if (tree[rt].cnt <= K) {                  // if K >= cnt, return sum of interval
            return tree[rt].sum;
        }
        if (l == r) {
            int min = Math.min(tree[rt].cnt, K);
            return l * min;                       // return sum of min number of value
        }
        int mid = l + (r - l) / 2;
        int res = 0;
        res += sumTopK(K, mid + 1, r, 2 * rt + 1); // add right interval first
        if (K > tree[2 * rt + 1].cnt) {
            res += sumTopK(K - tree[2 * rt + 1].cnt, l, mid, 2 * rt);
        }
        return res;
    }

    /**
     * Find the rank of this num in all values.
     * We need to find the sum of range [1, value-1]
     */
    public int rank(int value, int l, int r, int rt) {
        if (r < value) {
            return tree[rt].cnt;
        }
        int mid = l + (r - l) / 2;
        int res = 0;
        res += rank(value, l, mid, rt * 2);
        if (mid < value - 1) res += rank(value, mid + 1, r, rt * 2 + 1);
        return res;
    }

    /**
     * Find the largest num that is smaller than this value.
     */
    public int lowerKey(int value, int l, int r, int rt) {
        if (r < value) {
            if (tree[rt].cnt != 0) {
                return findRightMost(l, r, rt);// must exist such a value
            } else {
                return -1;                   // doesn't exist
            }
        }
        int mid = l + (r - l) / 2;
        if (mid + 1 < value && tree[rt * 2 + 1].cnt != 0) { // check right interval first
            int res = lowerKey(value, mid + 1, r, rt * 2 + 1);
            if (res != -1) { // if find in right interval
                return res;
            }
        }
        return lowerKey(value, l, mid, rt * 2);     // find left interval.
    }

    /**
     * Find the right most value in this interval.
     * must exist such value.
     */
    private int findRightMost(int l, int r, int rt) {
        if (l == r) {
            return l;
        }
        int mid = l + (r - l) / 2;
        if (tree[rt * 2 + 1].cnt != 0) {
            return findRightMost(mid + 1, r, rt * 2 + 1);
        } else {
            return findRightMost(l,mid, rt * 2);
        }
    }

    /**
     * Find the smallest num larger than this value.
     */
    public int higherKey(int value, int l, int r, int rt) {
        if (l > value) {
            if (tree[rt].cnt != 0) {
                return findLeftMost(l, r, rt);
            } else {
                return -1;            // doesn't exist
            }
        }
        int mid = l + (r - l) / 2;
        if (mid > value && tree[rt * 2].cnt != 0) {
            int res = higherKey(value, l, mid, rt * 2);
            if (res != -1) {
                return res;
            }
        }
        return higherKey(value, mid + 1, r, rt * 2 + 1);
    }

    /**
     * Find left most value in this interval.
     */
    private int findLeftMost(int l, int r, int rt) {
        if (l == r) {
            return l;
        }
        int mid = l + (r - l) / 2;
        if (tree[rt * 2].cnt != 0) {
            return findLeftMost(l, mid, rt * 2);
        } else {
            return findLeftMost(mid + 1, r, rt * 2 + 1);
        }
    }
}
