import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/26/21 - 23:59
 */

class Solution4 {
    // 将int[]转为String，按照0分割
    // Java不行，转化的String自动加","，影响split

    public int findMaxConsecutiveOnes(int[] nums) {
        String s = Arrays.toString(nums);
        String[] ss = s.split("0");
        System.out.println(s);
        System.out.println(Arrays.toString(ss));
        int maxLen = 0;

        for (String sss : ss) {
            maxLen = Math.max(maxLen, sss.length());
        }

        return maxLen;
    }
}
