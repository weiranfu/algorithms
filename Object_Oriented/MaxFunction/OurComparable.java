public interface OurComparable<T> {
    /**
     * Return negative number if this < o.
     * Return 0 if this equals o.
     * Return positive number if this > o.
     */
    int compareTo(T o);
}
