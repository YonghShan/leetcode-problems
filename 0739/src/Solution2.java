import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/3/21 - 16:03
 */
public class Solution2 {
    // 建立一个长度为71的array，这个array的index对应温度，值对应T中相应温度的index
    /* Runtime: 13ms      O(nw), where n is T.length, w is next.length. Since w is at most 71, O(nw) = O(71n) = O(n)
       Memory: 46.9MB     O(n+w) = O(n+71) = O(n)
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        int[] next = new int[101]; // 实际只是用index从30～100这一段, i.e. next.length <= 71
        Arrays.fill(next, Integer.MAX_VALUE);

        // 从右向左遍历T backward pass
        for (int i = T.length-1; i >= 0; i--) {
            int warmer_idx = Integer.MAX_VALUE;
            for (int j = T[i]+1; j < next.length; j++) {
                if (next[j] < warmer_idx) warmer_idx = next[j]; // 可以提前break，也可以不管，即使循环全部结束，warmer_idx一定是最靠近i的值
            }
            if (warmer_idx != Integer.MAX_VALUE) res[i] = warmer_idx - i;
            next[T[i]] = i;
        }

        return res;
    }
}
