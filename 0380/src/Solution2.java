import java.util.*;

/**
 * @author YonghShan
 * @date 4/15/21 - 22:12
 */
class RandomizedSet2 {
    // 修改Solution1的add()和remove(), 实现O(1)
    // 改用HashMap，记录val以及该val在加入list时在list中的index
    // 在从list中删除某val时，首先从HashMap中获取index，然后将list此时最后一位val放在index处（list.set(index, val)），然后删除最后一位
    /* Runtime: 7ms (faster than 99.96%)     O(1)
       Memory: 43.8MB (less than 66.03%)     O(n) for HashMap and Integer array
     */
    private HashMap<Integer, Integer> map;
    private List<Integer> list;
    private Random random_num = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet2() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size()); // 在map中记录该val在list中的index
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        int lastElement = list.get(list.size()-1);
        map.put(lastElement, index); // 更新原lastElement在list中的index
        list.set(index, lastElement);  // 将list第index位的元素置为最后一个元素
        map.remove(val);  // 从map中删去val
        list.remove(list.size()-1); // 从list中删去原最后一位元素
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int len = map.size();
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

