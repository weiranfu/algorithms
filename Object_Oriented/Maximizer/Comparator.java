public interface Comparator<T> {
    /**
     * Return negative number if this < o.
     * Return 0 if this equals o.
     * Return positive number if this > o.
     */
    int compare(T o1, T o2);
}
