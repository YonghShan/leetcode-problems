/**
 * @author YonghShan
 * @date 4/13/21 - 23:48
 */
import java.util.ArrayList;
import java.util.Collections;

class TwoSum2 {
    // Sorted List
    /* Runtime: O(1) for add() and O(nlogn) for find()
       Memory:  O(n)
     */
    private ArrayList<Integer> nums;
    private boolean is_sorted;

    /** Initialize your data structure here. */
    public TwoSum2() {
        this.nums = new ArrayList<Integer>();
        this.is_sorted = false;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.nums.add(number);
        this.is_sorted = false;
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (!this.is_sorted) {
            Collections.sort(this.nums);
            this.is_sorted = true;
        }
        int low = 0, high = this.nums.size() - 1;
        while (low < high) {
            int twosum = this.nums.get(low) + this.nums.get(high);
            if (twosum < value)
                low += 1;
            else if (twosum > value)
                high -= 1;
            else
                return true;
        }
        return false;
    }
}
