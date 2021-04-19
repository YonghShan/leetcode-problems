import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/13/21 - 22:51
 */
public class Solution1_2 {
    // Merger Sort Top-down Recursion 官方
    /* Runtime: 12ms    O(nlogn), where n is the length of nums
       Memory: 54MB     O(n)
     */
    public int [] sortArray(int [] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int pivot = nums.length / 2;
        int [] left_list = sortArray(Arrays.copyOfRange(nums, 0, pivot));
        int [] right_list = sortArray(Arrays.copyOfRange(nums, pivot, nums.length));
        return merge(left_list, right_list);
    }

    public int [] merge(int [] left_list, int [] right_list) {
        int [] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0, right_cursor = 0, ret_cursor = 0;

        while (left_cursor < left_list.length &&
                right_cursor < right_list.length) {
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }
        // append what is remain the above lists
        while (left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }
        while (right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }
}

