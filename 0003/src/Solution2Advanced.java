import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/12/21 - 11:20
 */
public class Solution2Advanced {
    //  在Solution2的基础上，通过引入一个变量，来构造Sliding Window
    public int lengthOfLongestSubstring(String s) {
    // Using HashMap
    /* Runtime: 4ms (faster than 88.17%)     O(n): index right will iterate n times
       Memory: 39.1M (less than 60.55%)      O(min(m, n)): the size of the string n and the size of the charset/alphabet m.
     */
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)) + 1); // e.g. "abba": 到right=2时，left更新到(1+1=)2；到right=3时，left要保持在2，而不是变成(0+1=)1
            }
            maxLen = Math.max(maxLen,  right - left + 1);
            map.put(s.charAt(right), right);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) {
    // int[26] for Letters 'a' - 'z' or 'A' - 'Z'
    // int[128] for ASCII
    // int[256] for Extended ASCII
    /* Runtime: 3ms (faster than 90.00%)     O(n): index right will iterate n times
       Memory: 39.3M (less than 41.79%)      O(m), m is the size of the charset
     */
        Integer[] chars = new Integer[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);

            Integer index = chars[r];
            if (index != null && index >= left) {  // 此时，重复元素出现；相对应上面method的left = Math.max(left, map.get(s.charAt(right)) + 1);
                left = index + 1;
            }

            res = Math.max(res, right - left + 1);

            chars[r] = right;
            right++;
        }

        return res;
    }
}
