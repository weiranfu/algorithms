package Tree.SegmentTree;

import java.util.*;

/**
 * 离散化
 */
public class Discretization {

    List<Integer> list;
    int N;

    public Discretization(int[] nums) {
        int n = nums.length;
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(nums[i]);
        }

        list = new ArrayList<>(new HashSet<>(list));      // remove duplicates
        Collections.sort(list);                           // sort
        N = list.size();

    }

    /**
     * Find the discretized index
     */
    private int find(int x) {
        int l = 0, r = N - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) == x) return mid + 1;       // return mid + 1 since SegTree/BIT is 1 based
            else if (list.get(mid) > x) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }
}
