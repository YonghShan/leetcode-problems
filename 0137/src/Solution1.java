/**
 * @author YonghShan
 * @date 6/29/21 - 20:51
 */
public class Solution1 {
    // 确定只出现一次的数字的每一个二进制位的值是0还是1
    /* Runtime: 1ms (faster than 80.17%)      O(32n) = O(n)
       Memory: 37.9MB (less than 99.96%)      O(1)
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {  // 数组中的元素都在int（即32位整数）范围内
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);  // 将num右移i位后和1与，即可得到该num在第i位的二进制值
            }
            if (total % 3 != 0) { // 如果取余结果为0，不需要更改ans中对应位的值，因为默认即为0
                // 但是取余结果若为1，则需要更改
                ans |= (1 << i);  // 将1向左移i位后，变为第i位为1，其余全为0的数；再与ans或，即可将ans的第i位置为1
            }
        }
        return ans;
    }
}
