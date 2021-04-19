import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/21/21 - 15:30
 */
public class Solution3Advanced1 {
    // Stack: 单调增栈
    // Solution3中在for嵌套while循环结束后，栈中保持ascending order，还需要一个操作基本相同的while循环依次出栈计算
    // 可以通过在heights[]末尾加入一个高度为0的bar，使得for循环到末尾时，一定触发其中嵌套的while循环完成栈中所有bar的出栈计算
    // 要在stack中提前加入-1指明结束的做法，也可以通过在heights[]开头加入一个高度为0的bar代替
    /* Runtime: 18ms  O(n)
       Memory: 54.MB O(n)
     */
    public int largestRectangleArea(int[] heights) {
        // 在heights[]首尾各加入一个高度为0的bar
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) { // 高度相等也可以入栈，继续拓宽右边界
                max = Math.max(max, newHeights[stack.pop()] * (i-stack.peek()-1));
            }
            stack.addFirst(i);
        }

        return max;
    }
}
