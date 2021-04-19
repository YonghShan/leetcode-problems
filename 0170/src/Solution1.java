import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/13/21 - 23:45
 */
class TwoSum {
    // HashMap<val, count>
    /* Runtime: 84ms (faster than 71.26%)    O(1) for add() and O(n) for find()
       Memory: 46.8MB (less than 34.67%)     O(n)
     */
    private HashMap<Integer, Integer> map;

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Integer i : map.keySet()) {
            if (value - i == i) {
                if (map.get(i) > 1) return true;
            } else {
                if (map.containsKey(value - i)) return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
