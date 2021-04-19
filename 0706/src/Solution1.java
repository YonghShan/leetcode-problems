import java.util.Arrays;

/**
 * @author YonghShan
 * @date 4/6/21 - 14:51
 */
class MyHashMap {
    // Array as bucket
    // 0 <= key <= 1000000: let the number of bucket is 1000, the capacity of per bucket is 1000, then the hash function is y = x % 1000
    /* Runtime: 38ms (faster than 21.56%)   O((N/K)^2) = O((10^6 / 1000)^2)
       Memory: 52.9MB (less than 5.12%)
     */
    private int[][] arr = new int[1001][1001];

    /** Initialize your data structure here. */
    public MyHashMap() {
        for (int i = 0; i < 1001; i++) {  // Initialization costs O((N/1000)^2)
            Arrays.fill(arr[i], -1);
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int bucketIndex = key / 1000;
        int locIndex = key % 1000;
        arr[bucketIndex][locIndex] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int bucketIndex = key / 1000;
        int locIndex = key % 1000;
        return arr[bucketIndex][locIndex];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int bucketIndex = key / 1000;
        int locIndex = key % 1000;
        arr[bucketIndex][locIndex] = -1;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

