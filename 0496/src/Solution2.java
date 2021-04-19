import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/21/21 - 21:41
 */
public class Solution2 {
    // 对nums2采用单调减栈，得到nums2中每个元素其后第一个更大值的index
    // 参考[84]Solution3的处理
    /* Runtime: 3ms (faster than 82.86%)   O(nums1.length + nums2.length)
       Memory: 39.2MB (less than 62.62%)   O(nums1.length)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> mapping = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                mapping.put(nums2[stack.pop()], nums2[i]);
            }
            stack.addFirst(i);
        }
        // for循环结束后，stack中不为空，但是elements按照descending order排列，即这些elements在nums2中其后都没有比它们大的数
        // 所以它们在mapping中对应的value都为-1
        while (!stack.isEmpty()) mapping.put(nums2[stack.pop()], -1);

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = mapping.get(nums1[i]);
        }
        return res;
    }
}
