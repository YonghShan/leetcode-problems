import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/3/21 - 16:46
 */
public class Solution3 {
    // Stack with Forward pass: 单调减栈
    /* Runtime: 12ms    O(n), where n is T.length
       Memory: 47.9MB   O(n): in worst case, the elements in T is in decreasing order, then the index of them are accumulated in the stack
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> stack = new ArrayDeque<>();

        // forward pass：构建单减栈
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) res[stack.peek()] = i - stack.pop();
            stack.addFirst(i);
        }

        return res;
    }
}
