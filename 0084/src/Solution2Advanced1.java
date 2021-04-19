/**
 * @author YonghShan
 * @date 3/20/21 - 23:12
 */
public class Solution2Advanced1 {
    // Divide & Conquer: Solution2用helper()实现
    // The rectangle with maximum area will be the maximum of:
    //      1. The widest possible rectangle with height equal to the height of the shortest bar;
    //      2. The largest rectangle confined to the left of the shortest bar (subproblem);
    //      3. The largest rectangle confined to the right of the shortest bar (subproblem).
    /* Runtime: TLE：91/96， 稍微提高一点。。。 O(nlogn)
                                            Worst Case: O(n^2).
                                            If the numbers in the array are sorted, we don't gain the advantage of divide and conquer.
       Memory: O(n). Recursion with worst case depth n.
     */
    public int calculateArea(int[] heights, int start, int end) {
        if (start > end)
            return 0;
        int minindex = start;
        for (int i = start; i <= end; i++)
            if (heights[minindex] > heights[i])
                minindex = i;
        return Math.max(heights[minindex] * (end - start + 1),
                Math.max(calculateArea(heights, start, minindex - 1),
                        calculateArea(heights, minindex + 1, end))
        );
    }

    public int largestRectangleArea(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }
}
