/**
 * @author YonghShan
 * @date 4/29/21 - 10:51
 */
public class Solution2 {
    // Records Letter Seen
    /* Runtime: O(n), where n = letters.length
       Memory: O(1)
     */
    public char nextGreatestLetter(char[] letters, char target) {
        boolean[] seen = new boolean[26];
        for (char c: letters)
            seen[c - 'a'] = true;

        while (true) {
            target++;
            if (target > 'z') target = 'a';
            if (seen[target - 'a']) return target;
        }
    }
}
