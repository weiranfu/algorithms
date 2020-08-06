package TwoPointers;

import java.util.HashMap;
import java.util.Map;

public class ExactlyKDistinctIntegers {

    /**
     * Use upper to shrink window.
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int upper = 0;
        for (int i = 0, j = 0; i < n; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            while (map.size() > K) {
                map.remove(A[upper]);       // currently cnt[A[upper]] == 1, so we remove A[upper] from map to decrease map size
                upper++;
                j = upper;
            }
            if (map.size() == K) {          // a valid window
                while (upper < i && map.get(A[upper]) > 1) {  // try to shrink window if there're at least 2 integers in the window
                    map.put(A[upper], map.get(A[upper]) - 1);
                    upper++;
                }
                res += upper - j + 1;       // count number of subarray ending with A[i]
            }
        }
        return res;
    }

    /**
     * Convert to at most K distinct integers.
     */
    public int subarraysWithKDistinct2(int[] A, int K) {
        return subarraysAtMostK(A, K) - subarraysAtMostK(A, K - 1);
    }

    private int subarraysAtMostK(int[] A, int K) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (map.getOrDefault(A[i], 0) == 0) K--;
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            while (j <= i && K < 0) {
                map.put(A[j], map.get(A[j]) - 1);
                if (map.get(A[j]) == 0) K++;
                j++;
            }
            res += i - j + 1;					// count number of subarrays
        }
        return res;
    }
}
