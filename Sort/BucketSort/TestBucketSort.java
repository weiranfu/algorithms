package Sort.BucketSort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestBucketSort {

    @Test
    public void test() {
        double[] input = {4.12, 6.421, 0.0023, 3.0, 2.123, 8.122, 4.12, 10.09};
        double[] expected = {0.0023, 2.123, 3.0, 4.12, 4.12, 6.421, 8.122, 10.09};
        BucketSort.sort(input);
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Sorted: " + Arrays.toString(input));
    }
}
