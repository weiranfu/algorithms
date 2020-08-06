package Tree.SegmentTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
 * If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 *
 * top(K): Return the score sum of the top K players.
 *
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard).
 * It is guaranteed that the player was added to the leaderboard before calling this function.
 */
public class DynamicWeightSegTree {
    class Node {
        int sum;
        int cnt;
        int l; int r;
        Node left; Node right;
    }

    Map<Integer, Integer> map = new HashMap<>();
    int maxn = 1000000;
    Node root = new Node();

    public void addScore(int id, int score) {
        if (!map.containsKey(id)) {
            map.put(id, score);
            update(score, true, 0, maxn, root);
        } else {
            int s = map.get(id);
            update(s, false, 0, maxn, root);
            s += score;
            map.put(id, s);
            update(s, true, 0, maxn, root);
        }
    }

    public int top(int K) {
        return topK(K, 0, maxn, root);
    }

    public void reset(int id) {
        int s = map.get(id);
        map.remove(id);
        update(s, false, 0, maxn, root);
    }

    /* ********************************************* */

    private void pushDown(Node n) {
        if (n.left == null) {
            n.left = new Node();
            n.right = new Node();
        }
    }
    private void pushUp(Node n) {
        n.cnt = n.left.cnt + n.right.cnt;
        n.sum = n.left.sum + n.right.sum;
    }

    private void update(int s, boolean add, int l, int r, Node n) {
        if (l == r) {
            if (add) {
                n.cnt++;
                n.sum += s;
            } else {
                n.cnt--;
                n.sum -= s;
            }
            return;
        }
        int mid = l + (r - l) / 2;

        pushDown(n);                            // create new node

        if (s <= mid) update(s, add, l, mid, n.left);
        else update(s, add, mid + 1, r, n.right);

        pushUp(n);                              // push up
    }
    private int topK(int K, int l, int r, Node n) {
        if (n.cnt <= K) {
            return n.sum;       // if K >= cnt, return sum of interval
        }
        if (l == r) {
            int min = Math.min(K, n.cnt);
            return min * l;     // return sum of min number of value
        }
        int mid = l + (r - l) / 2;
        int res = 0;
        res += topK(K, mid + 1, r, n.right);
        if (n.right.cnt < K) {
            res += topK(K - n.right.cnt, l, mid, n.left);
        }
        return res;
    }
}
