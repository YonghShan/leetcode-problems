/**
 * @author YonghShan
 * @date 3/20/21 - 19:52
 */
public class Solution1 {
    // Brute Force: 由1个bar所能组成的最大的面积；
    //              由相邻的2个bar所能组成的最大的面积；
    //              ...
    //              由相邻的n个bar所能组成的最大的面积, where n is the length of heights
    // 先确定size，再确定start index，则end index为start index + size
    /* Runtime: TLE    O(n^3): 87/96
       Memory: O(1)
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int max = 0;
        for (int size = 1; size <= len; size++) {                 // 从1个bar开始到相邻的n个bar：O(n)
            for (int start = 0; start < len; start++) {           // O(n)
                if (start + size <= len) {                        // end = start+size：保证end不超出边界
                    int min = Integer.MAX_VALUE;
                    for (int i = start; i < start+size; i++) {    // O(n)
                        min = Math.min(min, heights[i]);
                    }
                    max = Math.max(max, min * size);        // 木桶效应
                }
            }
        }
        return max;
    }
}
