/**
 * @author YonghShan
 * @date 2/2/21 - 21:18
 */

class Solution2 {
    // 因为原数组是sorted，所以result[]的最后一位要么来自原数组第一位平方的结果，要么是最后一位的平方
    // Two Pointers: L指向首，R指向尾
    /* Runtime: 1ms    O(n)
       Memory: 41MB    O(n)
     */
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int L = 0;
        int R = len - 1;

        for(int i = len - 1; i >= 0; i--){
            if(Math.abs(nums[L]) > Math.abs(nums[R])) {
                result[i] = nums[L] * nums[L];
                L++;
            } else {
                result[i] = nums[R] * nums[R];
                R--;
            }
        }

        return result;
    }
}
