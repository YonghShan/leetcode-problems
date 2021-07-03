/**
 * @author YonghShan
 * @date 7/2/21 - 23:11
 */
public class Solution1 {
    // 区分只出现一次和出现两次的容易，关键是如何将两个出现一次的区分开
    /* Runtime: 1ms (faster than 96.30%)     O(n)
       Memory: 38.8MB (less than 95.06%)     O(1)
     */
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;   // 循环结束后，bitmask = x ^ y

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x_bitmask = 0;
        // bitmask which will contain only x
        for (int num : nums) {
            if ((num & diff) != 0) x_bitmask ^= num;
        }  // 循环结束后，x_bitmask = x

        // 已知bitmask = x ^ y, x_bitmaskt = x,
        // 则y = bitmask ^ x_bitmask = x ^ y ^ x = y
        return new int[]{x_bitmask, bitmask^x_bitmask};
    }
}
