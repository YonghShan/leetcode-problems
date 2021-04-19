/**
 * @author YonghShan
 * @date 3/20/21 - 21:35
 */
public class Solution1Advanced1 {
    // Brute Force: 由1个bar所能组成的最大的面积；
    //              由相邻的2个bar所能组成的最大的面积；
    //              ...
    //              由相邻的n个bar所能组成的最大的面积, where n is the length of heights
    // Solution1的做法是先确定size，再确定start index，则end index为start index + size （在确定size的情况下，需要频繁地更换start index）
    // 但是如果先确定start index，再逐渐增加size，（也就是将以第i位开始的所有不同长度的pair依次取到），
    // 每次增加size时，只需要判断新加入的数和已取得的min，即可知道当前pair的min，从而省去Solution1中最内层的循环，将TC降至O(n^2)
    /* Runtime: TLE    O(n^2): 91/96
       Memory: O(1)
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int max = 0;
        for (int start = 0; start < len; start++) {
            int min = Integer.MAX_VALUE;
            for (int end = start; end < len; end++) { // end从start开始增加的过程，就是Solution1中的size++，只是直接定义end而不是size，是为了省去start+size超界的判断
                min = Math.min(min, heights[end]);
                max = Math.max(max, min * (end-start+1)); // size = end-start+1
             }
        }
        return max;
    }
}
