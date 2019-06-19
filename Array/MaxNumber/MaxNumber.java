public class MaxNumber {
    /** Returns the maximum value from Array m */
    public static int max(int[] m) {
        int sum = 0;
        for (int k = 0; k < m.length; k += 1) {
            if (sum < m[k]) {
                sum = m[k];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
