import java.util.Map;

/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU 缓存机制
 */

// @lc code=start
class LRUCache {

    private int cap = 0;
    private Map<Integer, Integer> mapLRU = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (mapLRU.containsKey(key)) {
            int val = mapLRU.get(key);
            mapLRU.remove(key);
            mapLRU.put(key, val);
            return val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (mapLRU.get(key) != null) {
            mapLRU.remove(key);
        } else {
            if (mapLRU.size() == cap) {
                mapLRU.remove(mapLRU.entrySet().iterator().next().getKey());
            }
        }
        mapLRU.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

