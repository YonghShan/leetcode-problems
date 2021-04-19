/**
 * @author YonghShan
 * @date 3/3/21 - 15:11
 */
public class Solution1 {
    // Two Pointers
    /* Runtime: 1098ms   O(n^2)
      Memory: 46.8MB     O(n), where n = T.length, for storing the result
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            int count = 0;
            for (int j = i+1; j < T.length; j++) {
                if (T[j] <= T[i]) {
                    count++;
                } else {
                    res[i] = count+1;
                    break;
                }
            }
        }

        return res;
    }
}
