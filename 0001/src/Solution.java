/**
 * @author YonghShan
 * @date 1/18/21 - 23:53
 */
class Solution {
    // O(n^2)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result = new int[]{i,j};
                    //result[0] = i;
                    //result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
