
/**
 * @author YonghShan
 * @date 3/13/21 - 23:08
 */
public class Solution7 {
    // Bubble Sort: TLE
    /* Runtime:
       Memory:
     */
    public int[] sortArray(int[] nums) {
        bubbleSort(nums);
        return nums;
    }
    private void bubbleSort(int[] nums) {
        for (int k = nums.length - 1; k >= 1; k--) {
            for (int i = 0; i < k; i++) {
                if (nums[i] > nums[i + 1]) swap(nums, i, i + 1);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
