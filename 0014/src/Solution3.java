/**
 * @author YonghShan
 * @date 7/20/21 - 01:02
 */
public class Solution3 {
    // Vertical Scanning
    /* Runtime: 0ms                         O(S)
       Memory: 37.4MB (less than 43.10%)    O(1)
     */
    public String longestCommonPrefix(String[] strs) {
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) // strs[j]可能短于strs[0]
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
