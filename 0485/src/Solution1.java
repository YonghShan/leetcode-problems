/**
 * @author YonghShan
 * @date 1/26/21 - 22:57
 */

class Solution1 {
    // 双指针-滑动窗口
    /* Runtime: 2ms （合并后的while循环只用1ms)
       Memory: 40M
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = 0;
        int end = 0;
        int maxLen = 0;

//        // 写得很蠢！
//        for (int i = 0; i<nums.length; i++){
//            if (nums[i] == 0) {
//                maxLen = Math.max(maxLen, end - start); // 如果先end++再更新maxLen的话，那maxLen要改为Math.max(maxLen, end - start -1)，和下面的while循环一样
//                end ++;
//                start = i+1;
//            } else {
//                end ++;
//            }
//        }

        // 第一个for循环可以简写为：
        for (end = 0; end<nums.length; end++){
            if (nums[end] == 0) {
                maxLen = Math.max(maxLen, end - start);
                start = end+1;
            }
        }

//        // 第一个for循环可以简写为：
//        while (end < nums.length) {
//            if (nums[end++] ==0) {
//                maxLen = Math.max(maxLen, end - start -1);
//                start = end;
//            }
//        }

        return Math.max(maxLen, end - start);
    }
}
