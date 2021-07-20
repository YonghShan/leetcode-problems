/**
 * @author YonghShan
 * @date 7/20/21 - 01:02
 */
public class Solution2 {
    // Horizontal Scanning
    /* Runtime: 0ms                         O(S)
       Memory: 36.8MB (less than 89.71%)    O(1)
     */
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) { // 如果strs[i]中并不包含prefix或者包含prefix但位置不是在最开头，则需要修剪prefix
                prefix = prefix.substring(0, prefix.length()-1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
