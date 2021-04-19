import java.util.*;

/**
 * @author YonghShan
 * @date 2/2/21 - 00:20
 */
class Solution2 {
    // Use a Set（保证元素互异）and Delete (the first and second, if exists) maximums to get the third maximum
    /* Runtime: 5ms    O(N)
       Memory: 38M     O(N)
     */
    public int thirdMax(int[] nums) {
        // Put the input integers into a HashSet (to avoid duplicates).
        Set<Integer> setNums = new HashSet<>();
        for (int num : nums) setNums.add(num);

        // Find the first maximum.
        int maximum = Collections.max(setNums); // Time Complexity: O(N)

        // Check whether or not this is a case where we need to return the *maximum*.
        if (setNums.size() < 3)  return maximum;

        // Otherwise, continue on to finding the third maximum.
        setNums.remove(maximum);
        int secondMaximum = Collections.max(setNums); // Find the second maximum
        setNums.remove(secondMaximum);

        return Collections.max(setNums); // Find the third maximum
    }
}

