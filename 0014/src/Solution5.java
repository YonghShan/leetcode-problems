/**
 * @author YonghShan
 * @date 7/20/21 - 01:02
 */
public class Solution5 {
    // Binary Search the length of the longest common prefix
    /* Runtime: 0ms                         O(S·logm)
       Memory: 37.2MB (less than 52.94%)    O(1)
     */
    public String longestCommonPrefix(String[] strs) {
        // Step 1: find the minLen
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());

        // Step 2: Binary Search
        int left = 0;
        int right = minLen;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid == left) mid++; // 防止String S的长度为3，此时left=0，right=2，mid=1；如果满足条件，则left=mid=1，right=2，陷入循环
            if (isCommonPrefix(strs, mid)) left = mid;
            else right = mid-1;
        }

        return strs[0].substring(0, left);
    }

    public boolean isCommonPrefix(String[] strs, int len) {
        String prefix = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(prefix))
                return false;
        return true;
    }
}
