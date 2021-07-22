import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/26/21 - 23:59
 */

class Solution4 {
    // 将int[]转为String，按照0分割
    /* Runtime: 9ms     O(n)
       Memory: 40.1MB   O(n) for Stirng s
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i : nums) sb.append(i);
        String s = sb.toString();
        // String s = Arrays.toString(nums); // 不可以这么转换，数字[1,1,0,1,1]的输出结果为"[1,1,0,1,1,1]"，导致之后split出错
        String[] ss = s.split("0");
        int maxLen = 0;

        for (String sss : ss) {
            maxLen = Math.max(maxLen, sss.length());
        }

        return maxLen;
    }
}
