import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 3/2/21 - 22:30
 */
class Solution1 {
    // 从左到右遍历String，遇到左半部分则存入stack，遇到右半部分则判断其与栈顶元素是否匹配，匹配则继续遍历，不匹配则return false
    /* Runtime: 2ms     O(n), where n is the length of String
       Memory: 36.8MB   O(n), in the worst case, we will end up pushing all the brackets onto the stack. e.g. (((((((.
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 1) return false;

        Map<Character, Character> mappings = new HashMap<>();
        mappings.put('}', '{');
        mappings.put(']', '[');
        mappings.put(')', '(');

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (mappings.containsValue(s.charAt(i))) {
                stack.addFirst(s.charAt(i));
            } else {
                if (stack.isEmpty() || stack.pop() != mappings.get(s.charAt(i))) return false;
            }
        }

        return stack.isEmpty();
    }
}