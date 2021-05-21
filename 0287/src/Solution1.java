import java.util.Arrays;

/**
 * @author YonghShan
 * @date 5/17/21 - 15:19
 */
public class Solution1 {
    // 因为限制条件（non-modifiable and O(1) space), HashSet和Sort都不可以，采用[0142] Floyd's Algorithm
    /* Runtime: 5ms (faster than 31.73%)      O(n)
       Memory: 56.4MB (less than 26.95%)      O(1)
     */
    public int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        // Phase 1:
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);
        // 循环结束时，两者在intersection相遇

        // Phase 2：
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        // 循环结束时，两者在entrance of cycle相遇

        return tortoise;
    }
}
