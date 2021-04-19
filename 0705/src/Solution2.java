import java.util.LinkedList;

/**
 * @author YonghShan
 * @date 4/5/21 - 15:45
 */
class MyHashSet2 {
    // LinkedList as bucket
    /* Runtime: 13ms (faster than 68.18%) O(N/K) = O(10^6 / 769) = O(1300)：因为contains()方法可能要扫完一整个LinkedList
       Memory: 46MB (less than 77.06%)    O(K+M), where M is the number of unique values that have been inserted into the HashSet.
     */
    private int base = 769;
    private LinkedList[] bucketArray;

    /** Initialize your data structure here. */
    public MyHashSet2() {
        bucketArray = new LinkedList[base];
        for (int i = 0; i < base; i++) {
            bucketArray[i] = new LinkedList<Integer>();
        }
    }

    public int getBucketIndex(int key) {
        return key % base;
    }

    public void add(int key) {
        int bucketIndex = getBucketIndex(key);
        if (bucketArray[bucketIndex].indexOf(key) == -1) bucketArray[bucketIndex].add(key);

    }

    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        bucketArray[bucketIndex].remove((Integer) key); // LinkedList有两种remove：remove(int index)：删去该index的元素   remove(Object o)：删去该o
        // 这里要用的是第二种remove，所以要把key cast为Integer
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = getBucketIndex(key);
        return bucketArray[bucketIndex].contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
