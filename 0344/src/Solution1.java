/**
 * @author YonghShan
 * @date 3/10/21 - 00:11
 */
public class Solution1 {
    // Recursion: Let the helper() do the swap
    /* Runtime: 1ms     O(n)
       Memory: 47.5MB   O(n)
     */
    public void reverseString(char[] s) {
        helper(s, 0, s.length-1);
    }

    public void helper(char[] s, int i, int j) {
        if (i >= j) return;
        char temp = s[i];
        s[i++] = s[j];
        s[j--] = temp;
        helper(s, i, j);
    }
}
