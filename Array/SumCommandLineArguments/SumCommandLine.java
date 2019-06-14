/** Sum the command line arguments which we suppose are integers. */
public class SumCommandLine {
    public static void main(String[] args) {
        int sum = 0;
        for (String a: args) {
            sum = sum + Integer.parseInt(a);   // Integer.parseInt will change String to Int.
        }
        System.out.println(sum);
    }
}
