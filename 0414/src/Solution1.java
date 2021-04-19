import java.util.Arrays;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 2/1/21 - 21:35
 */

class Solution1 {
    // Use a HashMap （虽然HashMap是无序的，但是可以用key的值来保证输入数组的元素顺序）
    /* Runtime: 720ms   O(NlogN)
       Memory: 42M      O(N)
     */
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int key = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsValue(nums[i])) { // make sure the elements in the HashMap are all distinct
                map.put(key, nums[i]);
                key++;
            }
        }

        int size = map.size();
        return size >= 3 ? map.get(size - 3) : map.get(size-1);
    }
}