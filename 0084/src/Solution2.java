import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/20/21 - 22:15
 */
public class Solution2 {
    // Divide & Conquer:
    // The rectangle with maximum area will be the maximum of:
    //      1. The widest possible rectangle with height equal to the height of the shortest bar;
    //      2. The largest rectangle confined to the left of the shortest bar (subproblem);
    //      3. The largest rectangle confined to the right of the shortest bar (subproblem).
    /* Runtime: 如果min全部改为用heights[minIndex]表示，则TLE：89/96     O(nlogn)
                                                                    Worst Case: O(n^2).
                                                                    If the numbers in the array are sorted, we don't gain the advantage of divide and conquer.
       Memory: 不该min的话， Memory Limit Exceeded：89/96             O(n). Recursion with worst case depth n.
     */
    public int largestRectangleArea(int[] heights) {
        // Base case:
        if (heights.length == 1) return heights[0];

        // Step 1: Find the index of the shortest bar
        int minIndex = 0;
        int min = heights[0];
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] < min) {
                min = heights[i];
                minIndex = i;
            }
        }

        // Step 2: Divide & Conquer
        int[] leftHeights = Arrays.copyOfRange(heights, 0, minIndex);
        int maxLeft = (leftHeights.length == 0) ? 0 : Math.max(largestRectangleArea(leftHeights), min * minIndex);
        int[] rightHeights = Arrays.copyOfRange(heights, minIndex+1, heights.length);
        int maxRight = (rightHeights.length == 0) ? 0 : Math.max(largestRectangleArea(rightHeights), min * (heights.length-minIndex));
        int subMax = Math.max(maxLeft, maxRight);

        // Step 3: Combine:
        // Don't forget the case that the largest rectangle can be the wildest rectangle with height equal to the height of the shortest bar.
        return Math.max(subMax, min * heights.length);
    }
}
