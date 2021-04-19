import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/7/21 - 16:58
 */
class Solution2 {
    // 逆波兰表示法的思路：遇到']'之前的所有元素都进栈；遇到']'则pop操作数（这题是字母和数字），然后将计算结果（这题指的是重复了数字边的字母串）再入栈
    /* Runtime: 2ms
       O(maxK^countK⋅n), where maxK is the maximum value of k, countK is the count of nested k values and n is the maximum length of encoded string.
       Example, for s = 20[a10[bc]], maxK is 20, countK is 2 as there are 2 nested k values (20 and 10) .
       Also, there are 2 encoded strings a and bc with maximum length of encoded string ,n as 2.
       Memory: 39MB     O(sum(maxK^countK⋅n))
     */
    public String decodeString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']') { // 遇到']'之前的所有元素都进栈
                stack.addFirst(s.charAt(i));
            } else { // 遇到']'则pop操作数（这题是字母和数字）
                StringBuilder sb = new StringBuilder();
                // '['前的都是字母
                while (stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                stack.pop(); // 把'['丢出去
                int count = 0;
                int base = 1;
                // 取数字，数字可能有多位
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    int temp = stack.pop() - '0';
                    count += temp * base;
                    base *= 10;
                }
                // 计算结果：将字母串重复数字边
                StringBuilder sb2 = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    sb2.append(sb.toString());
                }
                // 将计算结果放回栈中
                for (int k = sb2.toString().length()-1; k >= 0; k--) {
                    stack.addFirst(sb2.toString().charAt(k));
                }
//                // 将计算结果和将计算结果放回栈中两步结合：不额外new StringBuilder sb2去放计算结果，而是直接将字母串重复放入栈中数字次
//                while (count != 0) {
//                    for (int j = sb.toString().size() - 1; j >= 0; j--) {
//                        stack.push(sb.toString().charAt(j));
//                    }
//                    k--;
//                }
            }
        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }
}