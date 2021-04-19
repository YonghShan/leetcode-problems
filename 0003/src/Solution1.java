import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/11/21 - 22:44
 */
public class Solution1 {
    // 从String的每一个字符向后遍历，寻找最长无重复子串
    /* Runtime: 100ms (faster than 12.04%)  O(n^2)
       Memory: 39.4MB (less than 33.94%)    O(maxLen)
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                } else {
                    break;
                }
            }
            maxLen = Math.max(maxLen, set.size());
        }

        return maxLen;
    }
}
