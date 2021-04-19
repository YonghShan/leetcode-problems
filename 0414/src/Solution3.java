import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/2/21 - 00:22
 */

class Solution3 {
    // Seen-Maximums Set (Use a Set to record the first, second, if exists, and third maximums)
    /* Runtime: 4ms    O(N)
       Memory: 41M     O(1) (Because seenMaximums can contain at most 3 items)
     */
    public int thirdMax(int[] nums) {
        Set<Integer> seenMaximums = new HashSet<>(); // to store the first/second/third maximum

        for (int i = 0; i < 3; i++) {
            Integer curMaximum = maxIgnoringSeenMaximums(nums, seenMaximums);
            if (curMaximum == null) return Collections.max(seenMaximums); // Since the filter i<3, if curMaximum is null, then the number of elements in seenMaximum must be lee than 3.
            seenMaximums.add(curMaximum);
        }

        return Collections.min(seenMaximums);
    }

    private Integer maxIgnoringSeenMaximums(int[] nums, Set<Integer> seenMaximums) {
        Integer maximum = null;
        for (int num : nums) {
            if (seenMaximums.contains(num)) continue; // ignoring all the previous maximums
            if (maximum == null || num > maximum) maximum = num;  // Integer和int比较，是Integer自动拆箱再和int比
        }
        return maximum;
    }
}
