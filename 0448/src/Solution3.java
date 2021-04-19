import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/2/21 - 18:15
 */

class Solution3 {
    // O(1) Space InPlace Modification Solution
    // For each element nums[i], mark the element at the corresponding location negative if it's not already marked so
    // e.g. nums[i] = a, 则将原数组中第a个元素取反；对于相同的nums[i]，只对相应位置的元素取反一次
    // 最后，原数组中符号为正的元素即为missing elements
    /* Runtime: 8ms  O(n)
       Memory: 63MB  O(1)
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            int index = Math.abs(nums[i])-1; // In case nums[i] has been taken negative, we take abs() on it.
            if (nums[index] > 0) nums[index] *= -1;
        }

        for (int j = 0; j < len; j++) {
            if (nums[j] > 0) list.add(j+1);
        }

        return list;
    }
}