package Heap;

/**
 * An implementation of a Min Heap.
 *
 * 1. insert an element:                             heap[++size] = x; up(size);
 * 2. get the min:                                   heap[1]
 * 3. remove min:                                    heap[1] = heap[size]; size--; down(1);
 * 4. remove Kth inserted element:                   heap[k] = heap[size]; size--; up(k); down(k);
 * 5. change Kth inserted element's priority:        heap[k] = x; up(k); down(k);
 */
public class Heap {

    int n;
    int[] heap, map, remap;  // map array is used to map inserted id to pos in heap
                             // remap array is used to map pos in heap back to inserted id.
    int size, id;

    public Heap(int[] A) {
        n = A.length;
        for (int i = 1; i <= n; i++) {
            heap[i] = A[i - 1];         // heap is 1 based.
        }
        map = new int[n + 1];
        remap = new int[n + 1];
        size = n;
        id = 0;

        heapify();
    }

    /**
     * The time complexity is O(n) !!!!!
     */
    private void heapify() {
        for (int i = size / 2; i > 0; i--) {
            down(i);
        }
    }

    private void down(int x) {
        int t = x;
        if (x * 2 <= size && heap[x * 2] < heap[t]) t = x * 2;
        if (x * 2 + 1 <= size && heap[x * 2 + 1] < heap[t]) t = x * 2 + 1;
        if (t != x) {
            heap_swap(t, x);
            down(t);
        }
    }

    private void up(int x) {
        while (x / 2 > 0 && heap[x / 2] > heap[x]) {
            heap_swap(x, x / 2);
            x /= 2;
        }
    }

    private void heap_swap(int a, int b) {
        swap(map, remap[a], remap[b]);          // swap map relationship
        swap(remap, a, b);                      // swap remap relationship
        swap(heap, a, b);                       // swap heap
    }
    private void swap(int[] A, int a, int b) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }

    /** Insert element */
    public void add(int x) {
        size++;
        id++;
        heap[size] = x;
        map[id] = size;
        remap[size] = id;
        up(size);
    }

    /** Get min */
    public int getMin() {
        return heap[1];
    }

    /** Remove min */
    public int poll() {
        int res = heap[1];
        heap_swap(1, size);
        size--;
        down(1);
        return res;
    }

    /** Remove Kth inserted element */
    public int removeK(int k) {
        k = map[k];                         // map to k pos in heap
        int res = heap[k];
        heap_swap(k, size);
        size--;
        down(k);                            // either up or down at k
        up(k);
        return res;
    }

    /** Change Kth inserted element's priority */
    public void changeK(int k, int p) {
        k = map[k];
        heap[k] = p;
        down(k);
        up(k);
    }
}
