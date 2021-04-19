import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/21/21 - 22:05
 */
public class Solution2Advanced {
    // 对nums2采用单调减栈，得到nums2中每个元素其后第一个更大值的index
    // 参考[84]Solution3Advanced1的处理
    /* Runtime: 3ms (faster than 82.86%)   O(nums1.length + nums2.length)
       Memory: 39.2MB (less than 62.62%)   O(nums1.length)
    */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 建立一个新的num2：在原nums2后添上一个Integer.MAX_VALUE
        int[] newnums2 = Arrays.copyOf(nums2, nums2.length+1);
        newnums2[nums2.length] = Integer.MAX_VALUE;

        HashMap<Integer, Integer> mapping = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < newnums2.length; i++) {
            while (!stack.isEmpty() && newnums2[i] > newnums2[stack.peek()]) {
                if (newnums2[i] != Integer.MAX_VALUE) {
                    mapping.put(newnums2[stack.pop()], newnums2[i]);
                } else {
                    mapping.put(newnums2[stack.pop()], -1);
                }
            }
            stack.addFirst(i);
        }
        // for循环结束后，stack中还剩最后一个对应Integer.MAX_VALUE的index，并不需要
//        Set<Map.Entry<Integer, Integer>> ms = mapping.entrySet();
//        for (Map.Entry entry : ms) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = mapping.get(nums1[i]);
        }
        return res;
    }
}
