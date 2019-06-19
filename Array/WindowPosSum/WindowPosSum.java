public class WindowPosSum {
    /** change a[i] to the sum of a[i] through a[i+n], but only if a[i] is positive. */
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i += 1) {
            int sum = 0;
            // Exclude a[j] if a[j] is negative.
            if (a[i] <= 0) {
               continue;
            }
            for (int j = i; j <= i + n; j += 1) {
                // If index is out of range, break.
                if (j > a.length - 1) {
                    break;
                }
                sum = sum + a[j];
            }
            a[i] = sum;
        }
    }
    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);
        // Should print [4, 8, -3, 13, 9, 4]
        System.out.println(java.util.Arrays.toString(a));
    }
}
