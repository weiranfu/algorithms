package Set;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ArraySet<T> implements Iterable<T>{
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }


    /** Returns true if this Set contains item x.
     */
    public boolean contains(T x) {
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /** Adds a new item x if x is not in this Set.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            //throw new IllegalArgumentException("Can't add null to a Set.");
            System.out.println("Can't add null to a Set.");
            return;
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /** Creates an ArraySet using of(a, b, c...) */
    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> set = new ArraySet<>();
        for (Glerp g : stuff) {
            set.add(g);
        }
        return set;
    }

    /** Returns the number of items in this Set. */
    public int size() {
        return size;
    }


    @Override
    /** Iterable ArraySet. */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    // This is not a static class.
    private class ArraySetIterator implements Iterator<T> {
        private int p;

        public ArraySetIterator() {
            p = 0;
        }

        @Override
        public boolean hasNext() {
            return p < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T returnItem = items[p];
            p += 1;
            return returnItem;
        }
    }

    /** Prints the elements separated by commas inside of curly braces. i.e {1, 2, 3, 4}.
     *  In order to build String quickly, use StringBuilder class*/
    /*
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            SB.append(items[i].toString());
            SB.append(", ");
        }
        SB.append(items[size - 1]);
        SB.append("}");
        return SB.toString();
    }
    */
    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        for (T x : this) {
            list.add(x.toString());
        }
        // String.join() takes in an delimiter and an iterable, returns a String.
        return "{" + String.join(", ", list) + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != size) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");

        // Test iterator().
        for (String str : s) {
            System.out.println(str);
        }
        // Test toString().
        System.out.println(s);
        // Test of()
        ArraySet<Integer> intSet = ArraySet.of(1, 2, 3, 4, 5);
        System.out.println(intSet);
    }
}
    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */