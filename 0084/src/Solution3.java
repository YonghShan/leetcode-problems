import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/21/21 - 10:06
 */
public class Solution3 {
    // Stack: 单调增栈：建立于Solution1Advanced2的思想之上
    // Solution1Advanced2中之所以TC为O(n^2)的原因，是因为对于任一bar都要既向左又向右去找寻它的最远左右边界，那如何只用找一边即可？
    // 单调增栈的使用保证了先入栈的bar都比后入栈的矮，也就是说只要没入栈的下一个bar比当前栈顶bar还高，那就允许入栈，因为这是在拓宽栈里所有bar的右边界
    // 而因为先入栈的bar都要矮，也就保证了对于栈里所有bar来说，左边界就是本身
    // 因此，当即将入栈的下一个bar要比栈顶bar矮时，说明栈顶bar的左右边界都确定了，所以将栈顶bar出栈计算maxArea
    // 如果即将入栈的下一个bar依旧比新的栈顶bar矮时，那就对新的栈顶bar重复上面出栈操作
    /* Runtime: 17ms  O(n)
       Memory: 52.3MB O(n)
     */
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                max = Math.max(max, heights[stack.pop()] * (i-stack.peek()-1));
            }
            stack.addFirst(i);
        }   // for循环结束后，此时stack中存储的值可能不连续，但是值所对应的heights是ascending order
        // heights[1,2,3,4,2,2,3,4]: 当for结束时，stack中为{-1,0,5,6,7}，但heights[0,5,6,7]=[1,2,3,4]为ascending order
        //   index:0,1,2,3,4,5,6,7
        while (stack.peek() != -1) {
            max = Math.max(max, (heights[stack.pop()] * (heights.length- stack.peek()-1)));
        }
        return max;
    }
}
