
/**
 * @author YonghShan
 * @date 3/13/21 - 23:07
 */
public class Solution5 {
    // Selection Sort
    /* Runtime: 1910ms
       Memory: 54.3MB
     */
    public int[] sortArray(int[] nums) {
        selectionSort(nums);
        return nums;
    }
    private void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }
            if (minIndex != i) swap(nums, i, minIndex);
        }
    }
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
