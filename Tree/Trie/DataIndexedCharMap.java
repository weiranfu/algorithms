package Tree.Trie;

import java.util.ArrayList;
public class DataIndexedCharMap<T> {
    private T[] items;
    public DataIndexedCharMap(int N) {
        items = (T[]) new Object[N];
    }
    public void add(char c, T item) {
        items[c] = item;
    }
    public T get(char c) {
        if (keys().contains(c)) {
            return null;
        }
        return items[c];
    }
    public ArrayList<Character> keys() {
        ArrayList<Character> al = new ArrayList<>();
        for (int i = 0; i < items.length; i += 1) {
            if (items[i] != null) {
                al.add((char) i);
            }
        }
        return al;
    }
}
