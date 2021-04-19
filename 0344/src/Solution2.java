/**
 * @author YonghShan
 * @date 3/10/21 - 00:12
 */
public class Solution2 {
    // Two pointers
    /* Runtime: 0ms    O(n)
       Memory: 46.1MB  O(1)
     */
    public void reverseString(char[] s) {
        int j = s.length-1;
        for (int i = 0; i < s.length; i++) {
            if (i >= j) break;
            char temp = s[i];
            s[i] = s[j];
            s[j--] = temp;
            if (i == j) break;
        }
    }
}
