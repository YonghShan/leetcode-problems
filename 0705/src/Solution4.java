import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/6/21 - 15:57
 */
class MyHashSet4 {
    // 和前面的区别：在constructor中并没有统一初始化MAX_LEN个bucket，而是在add()中需要往某个bucket中放时，才new出来
    // 缺点：remove()中的ArrayList.remove(int index)方法 takes O(n)
    // 解决：1. Swap: First, swap the element which we want to remove with the last element in the bucket.
    //               Then remove the last element. By this way, we successfully remove the element in O(1) time complexity.
    //      2. use a linked list instead of an array list
    private final int MAX_LEN = 100000; // the amount of buckets
    private List<Integer>[] set;      // hash set implemented by array

    /** Returns the corresponding bucket index. */
    private int getIndex(int key) {
        return key % MAX_LEN;
    }

    /** Search the key in a specific bucket. Returns -1 if the key does not existed. */
    private int getPos(int key, int index) {
        // Each bucket contains a list.
        List<Integer> temp = set[index];
        if (temp == null) {
            return -1;
        }
        // Iterate all the elements in the bucket to find the target key.
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i) == key) {
                return i;
            }
        }
        return -1;
    }

    /** Initialize your data structure here. */
    public MyHashSet4() {   // ATTENTION!!! 并没有一开始就初始化，而是到add()中需要该bucket时才初始化
        set = (List<Integer>[])new ArrayList[MAX_LEN];
    }

    public void add(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            // Add new key if key does not exist.
            if (set[index] == null) {
                set[index] = new ArrayList<Integer>();
            }
            set[index].add(key);
        }
    }

    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos >= 0) {
            // Remove the key if key exists.
            set[index].remove(pos);   // O(n)
        }
    }

    /** Returns true if this set did not already contain the specified element */
    public boolean contains(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        return pos >= 0;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
