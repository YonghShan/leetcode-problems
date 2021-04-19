import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/31/21 - 16:43
 */

class Solution1 {
    // 找到原数组中连续全为1的子串的长度，相邻两个两两拼接，输出最大值
    /* Runtime: 4ms O(N)
       Memory: 54M O(N)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = 0;
        int end = 0;
        int i = 0;
        int[] lens = new int[nums.length+1]; // 因为下面第一个for循环本是为了记录全为1的子串的长度，但是写的却是每当值为0时也会记下长度（=1），导致所需要的lens数组长度过长
        int maxLen = 1;

        if (nums.length == 1) return 1;

        for (end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                lens[i] = end - start;
                i++;
                start = end + 1;
            }
        }
        lens[i] = end - start;
        //System.out.println(Arrays.toString(lens));

        if (i == 0) return lens[i]; // 原数组全为1的情况，并不需要拼接/并不需要+1

        for (int j = 0; j < lens.length-1; j++) {
            if (lens[j] + lens[j+1] + 1 > maxLen) {
                maxLen = lens[j] + lens[j+1] + 1;
            }
        }

        return maxLen;
    }
}
