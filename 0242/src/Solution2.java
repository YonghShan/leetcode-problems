import java.util.Arrays;

/**
 * @author YonghShan
 * @date 9/26/21 - 20:23
 */
public class Solution2 {
    /* Runtime: O(nlogn)
       Memory: O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
