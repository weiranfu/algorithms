/**
 * A Max function that can take in an array of general data type Objects and
 * return the maximum object in the array
 */
import java.util.Comparator;

public class Maximizer {
    public static Comparable max(Comparable[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (items[maxIndex].compareTo(items[i]) < 0) {
                maxIndex = i;
            }
        }
        return items[maxIndex];
    }

    /**
     * In this maxC function, we use an interface Comparator to callback a help function compare().
     * However, we cannot write maxC(Comparable[] items, Comparator<Comparable> c),
     * because we cannot pass in Comparator<Dog> into Comparator<Comparable>.
     */
    public static Comparable maxC(Comparable[] items, Comparator c) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (c.compare(items[maxIndex], items[i]) < 0) {
                maxIndex = i;
            }
        }
        return items[maxIndex];
    }
}
