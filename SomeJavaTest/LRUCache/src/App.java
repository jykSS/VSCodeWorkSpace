import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.get(2);
        lruCache.put(2, 6);
        lruCache.get(1);
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        lruCache.get(1);
        lruCache.get(2);
        System.out.println(lruCache);
    }

    Map<Integer, Node> mapKey = new HashMap<>();
    Node head;
    Node last;
    private int capacity;
    AtomicInteger count = new AtomicInteger(0);

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.valueOf(capacity));
        }
        Node headTail = new Node(null, null);
        Node lastTail = new Node(null, null);
        headTail.next = lastTail;
        lastTail.pre = headTail;
        this.capacity = capacity;
        this.head = headTail;
        this.last = lastTail;
    }

    public int get(int key) {
        Node temp = mapKey.get(key);
        if (temp == null) {
            return -1;
        }
        deleteNode(temp);
        addToHead(temp);
        return temp.val;
    }

    private void deleteNode(Node temp) {
        Node pre = temp.pre;
        Node next = temp.next;
        pre.next = next;
        next.pre = pre;
        count.decrementAndGet();
        temp.pre = null;
        temp.next = null;
        mapKey.remove(temp.key);
    }

    public void put(int key, int value) {
        if (mapKey.get(key) != null) {
            deleteNode(mapKey.get(key));
        } else {
            if (count.get() >= capacity) {
                deleteLast();
            }
        }

        Node newNode = new Node(key, value);
        addToHead(newNode);
    }

    private void addToHead(Node newNode) {
        Node temp = head.next;
        head.next = newNode;
        newNode.pre = head;
        newNode.next = temp;
        temp.pre = newNode;
        mapKey.put(newNode.key, newNode);
        count.incrementAndGet();
    }

    private void deleteLast() {
        Node del = last.pre;
        Node pre = del.pre;
        pre.next = last;
        last.pre = pre;
        del.next = null;
        del.pre = null;
        count.decrementAndGet();
        mapKey.remove(del.key);
    }

    public class Node {

        Integer key;
        Integer val;

        public Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }

        Node pre;
        Node next;

    }

}
/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
