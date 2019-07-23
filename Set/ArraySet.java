import java.util.Iterator;

public class ArraySet<T> implements Iterable<T>{
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }


    /* Returns true if this Set contains item x.
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

    /* Adds a new item x if x is not in this Set.
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

    /* Returns the number of items in this Set. */
    public int size() {
        return size;
    }


    @Override
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

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");

        System.out.println(s.contains("horse"));
        System.out.println(s.size());
        Iterator<String> iter = s.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        for (String str : s) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */