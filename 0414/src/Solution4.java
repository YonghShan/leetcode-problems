import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/2/21 - 00:38
 */

class Solution4 {
    // Keep Track of 3 Maximums Using a Set
    // 把原数组中的元素依次放入一个Set中，当Set的size>3时，删去其中最小值
    /* Runtime: 5ms    O(N)
       Memory: 42M     O(1) (Because maximums can contain at most 4 items)
     */
    public int thirdMax(int[] nums) {
        Set<Integer> maximums = new HashSet<Integer>();

        for (int num : nums) {
            maximums.add(num);
            if (maximums.size() > 3) maximums.remove(Collections.min(maximums));
        }

        if (maximums.size() == 3) return Collections.min(maximums);

        return Collections.max(maximums);
    }
}