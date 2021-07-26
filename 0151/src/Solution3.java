import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 7/26/21 - 00:39
 */
public class Solution3 {
    // Do not use built-in method: Stack of Words
    /* Runtime: 5ms (faster than 69.62%)    O(n)
       Memory: 39.4MB (less than 47.75%)    O(n)
     */
    public String reverseWords(String s) {
        int left = 0, right = s.length()-1;

        // remove leading and trailing spaces
        while (left <= right && s.charAt(left) == ' ') left++;
        while (left <= right && s.charAt(right) == ' ') right--;

        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        // push word by word in front of stack
        while (left <= right) {
            char c = s.charAt(left);

            if (c == ' ' && sb.length() != 0) { // 说明找到了一个word
                stack.addFirst(sb.toString());
                sb.setLength(0);
            } else if (c != ' ') {
                sb.append(c);
            }
            left++;
        }
        stack.addFirst(sb.toString());

        return String.join(" ", stack);
    }
}
