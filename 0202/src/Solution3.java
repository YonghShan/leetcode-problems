import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/9/21 - 00:18
 */
public class Solution3 {
    // Math: Hardcoding the only cycle
    // There's only one cycle: 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4.
    // All other numbers are on chains that lead into this cycle, or on chains that lead into 11.
    /* Runtime: O(logn)
       Memory: O(1)
     */
    private static Set<Integer> cycleMembers = new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNext(n);
        }
        return n == 1;
    }
}
