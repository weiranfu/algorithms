package Sort.BucketSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void sort(double[] x) {
        bucketSort(x);
    }

    public static void bucketSort(double[] x) {
        double max = x[0];
        double min = x[0];
        for (double i : x) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        double d = max - min;
        // Initialize buckets
        int bucketNum = x.length;
        List<Double>[] buckets = new List[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new ArrayList<>();
        }
        // Push items into buckets.
        for (double i : x) {
            int pos = (int) ((i - min) / d * (bucketNum - 1));
            buckets[pos].add(i);
        }
        // Sort every bucket.
        for (List<Double> bucket : buckets) {
            Collections.sort(bucket);
        }
        // Get sorted items.
        int index = 0;
        for (List<Double> bucket : buckets) {
            for (double i : bucket) {
                x[index++] = i;
            }
        }
    }
}
