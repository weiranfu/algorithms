public class Merge {
    // Sort strings destructively.
    public static void sort(String[] x) {
        mergeSort(x, 0, x.length);
    }

    public static void merge(String[] x, int start, int mid, int end) {
        String[] temp = new String[end - start];
        int i = 0;
        int p1 = start;      // Two pointers.
        int p2 = mid;
        while (p1 < mid && p2 < end) {
            if (x[p1].compareTo(x[p2]) < 0) {
                temp[i] = x[p1];
                i += 1;
                p1 += 1;
            } else {
                temp[i] = x[p2];
                i += 1;
                p2 += 1;
            }
        }
        while (p1 < mid) {        // If left hand array is left.
            temp[i] = x[p1];
            i += 1;
            p1 += 1;
        }
        while (p2 < end) {       // If right hand array is left.
            temp[i] = x[p2];
            i += 1;
            p2 += 1;
        }
        for (int j = 0; j < temp.length; j += 1) {     // Copy temp[] to array x.
            x[start + j] = temp[j];
        }
    }

    public static void mergeSort(String[] x, int start, int end) {
        if (end - start == 1) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(x, start, mid);
        mergeSort(x, mid, end);
        merge(x, start, mid, end);
    }

    public static void main(String[] args) {
        String[] input = new String[6];
        input[0] = "zz";
        input[1] = "d";
        input[2] = "t";
        input[3] = "k";
        input[4] = "a";
        input[5] = "r";
        for (String s : input) {
            System.out.print(s + " ");
        }
        System.out.println();
        sort(input);
        for (String s : input) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
