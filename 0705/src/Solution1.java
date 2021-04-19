import java.util.Arrays;

/**
 * @author YonghShan
 * @date 4/5/21 - 15:15
 */
class MyHashSet {
    // HashSet stored no repeated values
    // Array as bucket
    // 0 <= key <= 1000000: let the number of bucket is 1000, the capacity of per bucket is 1000, then the hash function is y = x % 1000
    /* Runtime: 34ms (faster than 23.38%)   O((N/K)^2) = O((10^6 / 1000)^2)
       Memory: 52.9MB (less than 10.71%)
     */
    private int[][] arr = new int[1001][1001];

    /** Initialize your data structure here. */
    public MyHashSet() {
        for (int i = 0; i < 1001; i++) {  // Initialization costs O((N/1000)^2)
            Arrays.fill(arr[i], -1);
        }
    }

    public void add(int key) {
        int bucketIndex = key / 1000;
        int locIndex = key % 1000;
        if (arr[bucketIndex][locIndex] == -1) { // 虽然直接arr[bucketIndex][locIndex] = key也可以，但是严格来说，按照HashSet不存储重复值的规定，只要该位置未被赋值，才insert
            arr[bucketIndex][locIndex] = key;
        }
        // arr[bucketIndex][locIndex] = key;
    }

    public void remove(int key) {
        int bucketIndex = key / 1000;
        int locIndex = key % 1000;
        if (arr[bucketIndex][locIndex] == key) { // 同理，直接arr[bucketIndex][locIndex] = -1也可以，但不严谨
            arr[bucketIndex][locIndex] = -1;
        }
        // arr[bucketIndex][locIndex] = -1;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = key / 1000;
        int locIndex = key % 1000;
        return arr[bucketIndex][locIndex] == key ? true : false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
