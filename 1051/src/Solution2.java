import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/31/21 - 00:05
 */

class Solution2 {
    // Time Complexity: O(NlogN)     Space Complexity: O(N)
    /* Runtime: 1ms
       Memory: 37MB
     */
    public int heightChecker(int[] heights) {
        int count = 0;
        int[] arr = heights.clone();
        Arrays.sort(arr);

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != arr[i]) count++;
        }
        return count;
    }
}