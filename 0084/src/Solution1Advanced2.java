/**
 * @author YonghShan
 * @date 3/21/21 - 12:05
 */
public class Solution1Advanced2 {
    // Solution3中单调栈的使用是建立于这个思想之上
    // 思路4：对每个bar依次向两边找比它高的bar，依次来拓宽以它为高时rectangle的width最大能有多少
    // 注意，要连续，如果该bar任一侧比它低，则这一边放弃寻找
    /* Runtime: TLE O(n^2): 93/96
       Memory:
     */
    public int largestRectangleArea(int[] heights) {
        int area = 0, n = heights.length;
        // 遍历每个bar，以当前bar的高度作为矩形的高h，
        // 从当前柱子向左右遍历，找到可能的最大宽度w
        for (int i = 0; i < n; i++) {
            int w = 1, h = heights[i], j = i;
            while (--j >= 0 && heights[j] >= h) {
                w++;
            }
            j = i;
            while (++j < n && heights[j] >= h) {
                w++;
            }
            area = Math.max(area, w * h);
        }

        return area;
    }
}
