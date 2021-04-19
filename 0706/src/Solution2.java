import java.util.LinkedList;

/**
 * @author YonghShan
 * @date 4/6/21 - 15:13
 */
class MyHashMap2 {
    // LinkedList<Pair<Integer, Integer>> as Bucket
    /* Runtime: 18ms (faster than 57.39%)   O(N/K) = O(10^6 / 2069)
       Memory: 45.5MB (less than 53.58%)    O((K+M) where K is the number of predefined buckets in the hashmap and M is the number of unique keys that have been inserted into the hashmap.
     */
    private int base = 2069;
    private Bucket[] bucketArray;

    /** Initialize your data structure here. */
    public MyHashMap2() {
        bucketArray = new Bucket[base];
        for (int i = 0; i < base; i++) {
            bucketArray[i] = new Bucket();
        }
    }

    public int getBucketIndex(int key) {
        return key % base;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int bucketIndex = getBucketIndex(key);
        bucketArray[bucketIndex].update(key, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int bucketIndex = getBucketIndex(key);
        return bucketArray[bucketIndex].get(key);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        bucketArray[bucketIndex].remove(key);
    }
}

class Pair<U, V> {
    public U first;  // 不可以为private
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

class Bucket {
    private LinkedList<Pair<Integer, Integer>> bucket;

    public Bucket() {
        bucket = new LinkedList<Pair<Integer, Integer>>();
    }

    public void update(Integer key, Integer value) {
        boolean isExist = false;
        for (Pair<Integer, Integer> pair : bucket) {
            if (pair.first.equals(key)) {  // 即该key之前已存过value
                pair.second = value; // update
                isExist = true;
            }
        }
        if (isExist == false) bucket.add(new Pair<Integer, Integer>(key, value)); // 易写错
    }

    public Integer get(Integer key) {
        for (Pair<Integer, Integer> pair : bucket) {
            if (pair.first.equals(key)) return pair.second;
        }
        return -1;
    }

    public void remove(Integer key) {
        for (Pair<Integer, Integer> pair : bucket) {
            if (pair.first.equals(key)) {
                bucket.remove(pair);
                break;
            }
        }
    }
}
