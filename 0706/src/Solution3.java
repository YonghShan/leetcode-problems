import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/6/21 - 15:51
 */

class MyHashMap3 {
    // 和前面的区别：在constructor中并没有统一初始化MAX_LEN个bucket，而是在put()中需要往某个bucket中放时，才new出来
    // 缺点：remove()中的ArrayList.remove(int index)方法 takes O(n)
    // 解决：1. Swap: First, swap the element which we want to remove with the last element in the bucket.
    //               Then remove the last element. By this way, we successfully remove the element in O(1) time complexity.
    //      2. use a linked list instead of an array list
    private final int MAX_LEN = 100000;             // the amount of buckets
    private List<Pair<Integer, Integer>>[] map;     // hash map implemented by array

    /** Returns the corresponding bucket index. */
    private int getIndex(int key) {
        return key % MAX_LEN;
    }

    /** Search the key in a specific bucket. Returns -1 if the key does not existed. */
    private int getPos(int key, int index) {
        // Each bucket contains a list.
        List<Pair<Integer, Integer>> temp = map[index];
        if (temp == null) {
            return -1;
        }
        // Iterate all the elements in the bucket to find the target key.
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).getKey() == key) {
                return i;
            }
        }
        return -1;
    }

    /** Initialize your data structure here. */
    public MyHashMap3() { // ATTENTION!!! 并没有一开始就初始化，而是到put()中需要该bucket时才初始化
        map = (List<Pair<Integer, Integer>>[])new ArrayList[MAX_LEN];
    }

    /** value will always be positive. */
    public void put(int key, int value) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            // Add new (key, value) pair if key is not existed.
            if (map[index] == null) {
                map[index] = new ArrayList<Pair<Integer, Integer>>();
            }
            map[index].add(new Pair(key, value));
        } else {
            // Update the value if key is existed.
            map[index].set(pos, new Pair(key, value));
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            return -1;
        } else {
            return map[index].get(pos).getValue();
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos >= 0) {
            map[index].remove(pos); // O(n)
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
