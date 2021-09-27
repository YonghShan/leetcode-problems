/**
 * @author YonghShan
 * @date 9/26/21 - 20:25
 */
public class Solution1Advanced {
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) return false;

        int[] alphabet = new int[26];
        for (int i = 0; i < sLen; i++) {
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }

        for (int num : alphabet) {
            if (num != 0) return false;
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) return false;

        int[] alphabet = new int[26];
        for (int i = 0; i < sLen; i++) alphabet[s.charAt(i) - 'a']++;
        for (int j = 0; j < tLen; j++) {
            alphabet[t.charAt(j) - 'a']--;
            if (alphabet[t.charAt(j) - 'a'] != 0) return false;
        }

        return true;
    }
}
