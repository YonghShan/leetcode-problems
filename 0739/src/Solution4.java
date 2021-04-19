import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/3/21 - 17:04
 */
public class Solution4 {
    // Stack with Backward pass
    /* Runtime: 12ms    O(n), where n = T.length
       Memory: 47MB     O(w), where w <= 71: in worst case, the elements in T is in increasing order, then w = 71
       Backward approach和Forward approach一样都stack都可能会堆积，但是backward pass不存在元素所对应的温度值相同的情况（即使T中有连续的相同温度值，stack中只保存最左边温度的index）
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> stack = new ArrayDeque<>();

        // backward pass: 逆向构建单减栈
        for (int i = T.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop(); // 现在要判断的T[i]比栈顶元素要大，所以一直pop元素，直到新的栈顶元素大于T[i]，此时栈顶元素-i即为res[i]
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.addFirst(i);
        }

        return res;
    }
}
