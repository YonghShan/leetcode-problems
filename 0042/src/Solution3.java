import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/22/21 - 12:18
 */
public class Solution3 {
    // 单减栈：pop the top of stack when height[curr] > height[top]   top is the old top of stack since it's been popped.
    // 计算积水量：width between the curr element and the new top of stack = curr - stack.peek() - 1;
    //           height which limit the rain = min(height[curr], height[stack.peek()]) - height[top]
    /* Runtime: 1ms     O(n)
       Memory: 38.8MB   O(n)
     */
    public int trap(int[] height) {
        int rainNum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(-1);
        for (int i = 0; i < height.length; i++) {
            while (stack.peek() != -1 && height[i] > height[stack.peek()]) {
                int tmp = height[stack.pop()];
                if (stack.peek() == -1) continue;
                rainNum += (Math.min(height[i], height[stack.peek()]) - tmp) * (i - stack.peek() - 1);
            }
            stack.addFirst(i);
        }
        // for循环结束时，stack中即使还有值没pop出来，也不用考虑，因为里面的值肯定是descending order，也就是积不了水的
        return rainNum;
    }
}
