
/**
 * @author YonghShan
 * @date 3/13/21 - 23:07
 */
public class Solution6 {
    // Insertion Sort
    /* Runtime: 1987ms
       Memory: 46.4MB
     */
    public int[] sortArray(int[] nums) {
        insertionSort(nums);
        return nums;
    }
    private void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (nums[j] >= nums[j - 1]) break;
                swap(nums, j, j - 1);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
