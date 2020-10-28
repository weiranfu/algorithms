package OOD.LRUCache;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node() {}
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
