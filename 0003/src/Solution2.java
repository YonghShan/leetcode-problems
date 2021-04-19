import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/11/21 - 22:57
 */
public class Solution2 {
    // 应该是和Solution1没什么区别
    /* Runtime: 76ms (faster than 16.13%)
       Memory: 39.2M (less than 41.79%)
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                maxLen = Math.max(maxLen, map.size());
            } else {
                i = map.get(s.charAt(i));
                map.clear();
            }
        }

        return maxLen;
    }
}
