import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    // 设置容量
    private int capacity;
    // 设置当前节点统计
    private int count;
    private Map<K, Node<K, V>> nodeMap;
    private Node<K, V> head;
    private Node<K, V> tail;

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache<>(5);
        lruCache.put("1", "1");
        lruCache.put("2", "1");
        lruCache.put("3", "1");
        lruCache.put("4", "1");
        lruCache.put("5", "1");
        lruCache.get("1");
        lruCache.get("1");
        lruCache.put("6", "1");
        System.out.println(lruCache);
    }

    /**
     * 设置缓存
     */
    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.valueOf(capacity));
        }
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        Node headNode = new Node(null, null);
        Node tailNode = new Node(null, null);
        headNode.next = tailNode;
        tailNode.pre = headNode;
        this.head = headNode;
        this.tail = tailNode;
    }

    /**
     * put 方法
     */
    public void put(K key, V value) {
        Node<K, V> node = nodeMap.get(key);
        // 先查有没有
        if (node == null) {
            // 创造新节点
            node = new Node(key, value);
            addNode(node);
            // 如果有的话就先检查是否要LRU
            if (count > capacity) {
                removeNode();
            }
        } else {
            // 把已经存在的节点提前
            moveNodeToHead(node);
        }
    }

    /**
     * 移动节点到投节点
     * 
     * @param node
     */
    private void moveNodeToHead(Node<K, V> node) {
        removeFromList(node);
        addToHead(node);
    }

    /**
     * 添加节点
     * 
     * @param node
     */
    private void addNode(Node<K, V> node) {
        addToHead(node);
        nodeMap.put(node.key, node);
        count++;
    }

    /**
     * 添加头节点
     * 
     * @param node
     */
    private void addToHead(Node<K, V> node) {
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    /**
     * 删除方法
     */
    private void removeNode() {
        Node node = tail.pre;
        removeFromList(node);
        nodeMap.remove(node.key);
        count--;
    }

    private void removeFromList(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        node.next = null;
        node.pre = null;
    }

    /**
     * get 方法
     */
    public Node<K, V> get(K key) {
        Node<K, V> node = nodeMap.get(key);
        if (node != null) {
            moveNodeToHead(node);
        }
        return node;
    }

    class Node<K, V> {
        K key;
        V value;
        Node pre;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
