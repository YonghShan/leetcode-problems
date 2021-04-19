import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @author YonghShan
 * @date 4/15/21 - 15:52
 */
class RandomizedSet {
    // 为GetRandom引入一个新的List (且要和HashSet同时赋值），HashSet无法实现随机
    // 如果到getRandom()才去创建list/array，会takes O(n)
    /* Runtime: 37ms (faster than 20.29%)    O(n) for list.remove()
       Memory: 43.8MB (less than 75.29%)     O(n) for HashMap and Integer array
     */
    private HashSet<Integer> set;
    private List<Integer> list;
    private Random random_num = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (set.contains(val)) return false;
        set.add(val);
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!set.contains(val)) return false;
        set.remove((Integer) val);
        list.remove((Integer) val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int len = set.size();
        // Integer[] arr = set.toArray(new Integer[0]);
        // ArrayList<Integer> list = new ArrayList<>(set);  // 都takes O(n)
        int index = random_num.nextInt(len);

        // return arr[index];
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
