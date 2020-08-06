package TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k,
 * you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * We cannot use Sliding Window here, cause there may be negative integers in array.
 * Instead we could cache number of subarrays with sum S in a map.
 * When we compute number of subarrays [i, j] with sum k,
 * sum(i,j) = sum(0,j) - sum(0, i)
 * the number of subarrays is map.get(sum - k)
 */
public class SubarraySumK {

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int preSum = 0;
        int res = 0;
        map.put(0, 1);                              // initial value for prefix sum
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            res += map.getOrDefault(preSum - k, 0);  // get the subarray ending with nums[i] with sum k
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }
}
