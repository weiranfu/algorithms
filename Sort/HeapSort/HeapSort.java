package Sort.HeapSort;

/**
 * For in-place heap sort:
 * 1. Heapify a max-heap in-place. (Attention: must be a max-heap)
 *    Sink every node to be a max-heap.
 * 2. Move the item at head of array to the tail of array,
 *    and move tail item to head, then sink this item.
 *
 * Time complexity: O(n*lgn),  the time for heapify is O(n) rather than O(logn)
 * Space complexity: in-place: O(1)
 */
public class HeapSort {

    int[] h;
    int n;

    public int[] sortArray(int[] nums) {
        h = nums;
        n = nums.length;
        for (int i = n / 2; i >= 0; i--) {          // 0 based heap
            down(i, n);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(0, i);
            down(0, i);                          // upper bound is i
        }
        return h;
    }

    private void down(int x, int len) {             // mind the upper bound len
        int t = x;
        if (x * 2 + 1 < len && h[x * 2 + 1] > h[t]) t = x * 2 + 1;
        if (x * 2 + 2 < len && h[x * 2 + 2] > h[t]) t = x * 2 + 2;
        if (t != x) {
            swap(t, x);
            down(t, len);
        }
    }

    private void swap(int a, int b) {
        int tmp = h[a];
        h[a] = h[b];
        h[b] = tmp;
    }
}
