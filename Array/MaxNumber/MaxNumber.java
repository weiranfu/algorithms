public class MaxNumber {
    /** Returns the maximum value from Array m */
    public static int max(int[] m) {
        int k = 0;
        int sum = 0;
        while (k < m.length) {
            if (sum < m[k]) {
                sum = m[k];
            }
            k = k + 1;
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
