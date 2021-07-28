/**
 * @author YonghShan
 * @date 7/26/21 - 15:14
 */
public class Solution1 {
    // [0151] Solution 2     要求不用Built-in Method选这个
    /* Runtime: 7ms (faster than 48.61%)   O(n)
       Memory: 40MB (less than 31.98%)     O(n)
     */
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        reverseEachWord(sb);
        return sb.toString();
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') end++;
            reverse(sb, start, end-1);
            start = ++ end;
        }
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            left++;
            right--;
        }
    }
}
