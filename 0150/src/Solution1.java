import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 3/3/21 - 21:36
 */
class Solution1 {
    // Stack: 从左到右遍历，如果是操作数就入栈，如果是操作符就从栈里pop出来两个操作数进行计算，把计算结果入栈作为新的操作数
    // 这道题的操作符只局限于"+、—、*、/"，一般情况如果有其他的单操作数或者多操作数的操作符存在，则需要通过查表确定要从栈中pop出几个操作数进行计算
    /* Runtime: 4ms     O(n), where n = String[].size
       Memory: 38.8MB   O((n+1)/2), in worst case, the stack will have all the numbers on it at the same time.
       This is never more than half the length of the input array. i.e There are m operands and m-1 operators, so n = m+m-1, m = (n+1)/2
     */
    public int evalRPN(String[] tokens) {
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        Deque<Integer> operands = new ArrayDeque<>();

        for (String token : tokens) {
            if (!operators.contains(token)) {
                operands.addFirst(Integer.parseInt(token));
            } else {
                int operand1 = operands.pop();
                int operand2 = operands.pop();
                int result = 0;
                switch (token) {
                    case "+" :
                        result = operand2 + operand1;
                        break;
                    case "-" :
                        result = operand2 - operand1;
                        break;
                    case "*" :
                        result = operand2 * operand1;
                        break;
                    case "/" :
                        result = operand2 / operand1;
                        break;
                }
//                if (token.equals("+")) {
//                    result = operand2 + operand1;
//                } else if (token.equals("-")) {
//                    result = operand2 - operand1;
//                } else if (token.equals("*")) {
//                    result = operand2 * operand1;
//                } else {
//                    result = operand2 / operand1; // 除法要注意谁是被除数，谁是除数
//                }
                operands.addFirst(result);
            }
        }

        // 因为题目说了输入的RPN表达式一定是valid，所以不用判断除数是否为0，不用判断结束时operands中是否只含有一个元素
        return operands.pop();
    }
}
