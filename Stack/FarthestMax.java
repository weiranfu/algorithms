package Stack;

import java.util.Stack;

/**
 * Farthest Smaller Element problem
 *
 * Given an array A of integers,
 * find the maximum length of tuple(i, j) that i < j and A[i] <= A[j]
 *
 * 思路：
 * Given an array, for every index, we need to find farthest smaller element on its left.
 */
public class FarthestMax {

    /**
     * Stack
     * We keep a decreasing stack to store the index of array because if we meet a larger item on the right of a smaller item,
     * that larger item will be useless. So we only add smaller item.
     * Then we scan from right to left to find the farthest smaller item.
     */
    public int maxLengthTuple2(int[] A) {
        int n = A.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || A[stack.peek()] > A[i]) {                // only add smaller item
                stack.push(i);
            }
        }
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {									// scan from right to left
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }


    /**
     * Two Pointers
     * We keep two pointers j, i (j <= i), if we find A[j] > max(A[i], A[i+1], ..., A[n])
     * A[j] will be useless, cause every A[i] and its right items will be smaller than A[j], j++
     */
    public int maxLengthTuple(int[] A) {
        int n = A.length;
        int[] rMax = new int[n];
        rMax[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(A[i], rMax[i + 1]);   // store the max value to the right
        }
        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j <= i && A[j] > rMax[i]) {      // j is useless
                j++;
            }
            res = Math.max(res, i - j);
        }
        return res;
    }
}
