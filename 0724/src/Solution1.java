/**
 * @author YonghShan
 * @date 6/25/21 - 17:12
 */
public class Solution1 {
    // 一定要注意pivot index可以为0或者nums.length-1
    /* Runtime: 1ms (faster than 100%)     O(n)
       Memory: 40.2MB (less than 18.98%)   O(1)
     */
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int rightSum = 0;

        for (int num : nums) {    // O(n)
            rightSum += num;
        }

        for (int i = 0; i < nums.length; i++) {     // O(n)
            rightSum -= nums[i];
            if (leftSum == rightSum) return i;
            leftSum += nums[i];
        }

        return -1;
    }
}
