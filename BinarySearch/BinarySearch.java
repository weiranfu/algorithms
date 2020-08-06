package BinarySearch;

public class BinarySearch {

    int[] A;
    int n;

    public BinarySearch(int[] A) {
        this.A = A;
        n = A.length;
    }

    /**
     * Template 1:  Find the smallest i, that A[i] >= key
     *
     * 当我们将区间[l, r]划分成[l, mid]和[mid + 1, r]时，
     * 其更新操作是r = mid或者l = mid + 1; 计算mid时不需要加1。
     */
    public int search(int key) {
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] >= key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return A[l] >= key ? l : -1;    // check A[l]
    }

    /**
     * Template 2:  Find the largest i, that A[i] < key
     *
     * 当我们将区间[l, r]划分成[l, mid - 1]和[mid, r]时，
     * 其更新操作是r = mid - 1或者l = mid;，to avoid infinite loop，计算mid时需要加 1。
     */
    public int search2(int key) {
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;      // mid + 1
            if (A[key] < key) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return A[l] < key ? l : -1;    // check A[l]
    }

    /**
     * Template 3: search a certain key
     */
    public int search3(int key) {
        int l = 0, r = n - 1;
        while (l <= r) {                        // l <= r
            int mid = l + (r - l) / 2;
            if (A[mid] == key) return mid;      // find
            else if (A[mid] < key) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Template 4: 浮点数的二分
     *
     * 给定一个浮点数n，求它的三次方根。结果保留6位小数(边界判断时应为1e-8)
     */
    public double search3(double x) {
        double l = -10000, r = 10000;
        while (r - l > 1e-8) {              // 控制精度
            double mid = (l + r) / 2;
            if (mid * mid * mid >= x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }



    /**
     * Find the first insertion pos that greater than or equal to key.
     * [1,2,2,2,3]   key = 2
     * return 1
     */
    public int lower_bound(int key) {
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] >= key) {      // if check(true)
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l == n ? -1 : l;
    }

    public int lower_bound2(int key) {
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] >= key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return A[l] >= key ? l: l + 1;
    }

    /**
     * Find the last position that smaller than key.
     * [1,2,2,2,3]   key = 2
     * Return 0
     */
    public int lower_key(int key) {
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] < key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;                    // return l - 1
    }

    /**
     * Find the first insertion pos that great than key.
     * [1, 2, 2, 2, 3]  key = 2
     * return 4
     */
    public int upper_bound(int key) {
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] > key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l == n ? -1 : l;
    }

    /**
     * Find the last pos that smaller or equal to key.
     * [1,2,2,2,3]   key = 2
     * Return 3
     */
    public int floor_key(int key) {
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] <= key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;                  // return l - 1
    }
}
