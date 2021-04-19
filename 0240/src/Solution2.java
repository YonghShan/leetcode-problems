/**
 * @author YonghShan
 * @date 3/14/21 - 21:29
 */
public class Solution2 {
    // Recursion: D&C 依据中间列的值来划分
    // Let height = n, width = m, and n≈m, then the total number of matrix x = n^2: T(x) = 2T(x/4) + sqrt(x), where sqrt(x) is the time for looking up pivotH
    // With Master Method Case 2:
    //      a = 2, b = 4, logb(a) = 0.5 = c, f(x) = sqrt(x) = Big_Theta(x^logb(a)) ==> T(x) = Big_Theta(x^c * log(x)) = O((n^2)^0.5 * log(n^2)) = O(nlogn)
    /* Runtime: 5ms (faster than 50.93%)     O(nlogn)
       Memory: 44.9MB (less than 31.60%)     O(logn)
     */
    private int[][] mat;
    private int tar;
    public boolean searchMatrix(int[][] matrix, int target) {
        mat = matrix;
        tar = target;
        return helper(0, 0, matrix.length-1, matrix[0].length-1);
    }

    public boolean helper(int topH, int topW, int bottomH, int bottomW) {
        if (topH > bottomH || topW > bottomW) return false;    // 因为m2中pivotW加1，这里判断是否越界; 当target不存在matrix中时，pivotH会越界
        if (tar < mat[topH][topW] || tar > mat[bottomH][bottomW]) return false; // target比matrix中的min还小或比max还大
        if (tar == mat[topH][topW] || tar == mat[bottomH][bottomW]) return true;
        if (topH == bottomH && topW == bottomW) {
            return mat[topH][topW] == tar ? true : false;
        }

        int pivotW = topW + (bottomW - topW) / 2;  // 取最中间列
        // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
        int pivotH = topH; // 从第一行开始
        for (; pivotH <= bottomH; pivotH++) {
            if (mat[pivotH][pivotW] == tar) return true;
            if (mat[pivotH][pivotW] > tar) break;
        }
        // 当target不存在matrix中时，pivotH会额外加1，导致越界

        // 因为不是均分，只用考虑第一象限和第三象限的submatrix即可
        boolean m2 = helper(topH, pivotW+1, pivotH-1, bottomW);  // 第一象限    这里pivotW不加1会陷入死循环，但是加1了要防止越界
        boolean m3 = helper(pivotH, topW, bottomH, pivotW-1);          // 第三象限    当target不存在matrix中时，pivotH会越界

        return m2 || m3;
    }
}
