package OOD.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> cache = new HashMap<>();
    int size;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    // Always add the new node right after head.
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Remove an existing node from the linked list.
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // Move certain node in between to the head. (change six pointers)
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Pop the current tail.
    private Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
            if (size > capacity) {
                Node tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        }
    }
}
