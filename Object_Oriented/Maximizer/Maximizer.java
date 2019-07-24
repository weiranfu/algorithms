/**
 * A Max function that can take in an array of general data type Objects and
 * return the maximum object in the array
 */
import java.util.Comparator;

public class Maximizer {
    public static <T> T max(Comparable<T>[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (items[maxIndex].compareTo((T) items[i]) < 0) {
                maxIndex = i;
            }
        }
        return (T) items[maxIndex];
    }

    /**
     * In this maxC function, we use an interface Comparator to callback a help function compare().
     * However, we cannot write maxC(Comparable[] items, Comparator<Comparable> c),
     * because we cannot pass in Comparator<Dog> into Comparator<Comparable>.
     */
    public static <T> T maxC(Comparable<T>[] items, Comparator<T> c) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (c.compare((T) items[maxIndex], (T) items[i]) < 0) {
                maxIndex = i;
            }
        }
        return (T) items[maxIndex];
    }
}
