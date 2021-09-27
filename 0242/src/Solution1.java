/**
 * @author YonghShan
 * @date 9/26/21 - 20:21
 */
public class Solution1 {
    /* Runtime: O(max(len, 26))
       Memory: O(26)
     */
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) return false;

        int[] alphabet = new int[26];
        for (int i = 0; i < sLen; i++) alphabet[s.charAt(i) - 'a']++;
        for (int j = 0; j < tLen; j++) alphabet[t.charAt(j) - 'a']--;

        for (int num : alphabet) {
            if (num != 0) return false;
        }

        return true;
    }
}
