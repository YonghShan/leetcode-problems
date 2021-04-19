/**
 * @author YonghShan
 * @date 3/14/21 - 16:00
 */
public class Solution1 {
    // Recursion: D&C 均分
    // Let height = n, width = m, and n ≈ m, then the total number of matrix x = n^2: T(x) = 4T(x/4) + 0
    // With Master Method Case 1:
    //      a = 4, b = 4, logb(a) = 1, f(x) = 0 = O(x^logb(a)) ==> T(x) = O(x^logb(a)) = O(x) = O(n^2)
    /* Runtime: 5ms (faster than 50.93%)    O(n^2)
       Memory: 44.4MB (less than 93.66%)    O(n)
     */
    private int[][] mat;
    private int tar;
    public boolean searchMatrix(int[][] matrix, int target) {
        mat = matrix;
        tar = target;
        return helper(0, 0, matrix.length-1, matrix[0].length-1);
    }

    public boolean helper(int topH, int topW, int bottomH, int bottomW) {
        if (tar < mat[topH][topW] || tar > mat[bottomH][bottomW]) return false; // target比matrix中的min还小或比max还大
        if (tar == mat[topH][topW] || tar == mat[bottomH][bottomW]) return true;
        if (topH == bottomH && topW == bottomW) {
            return mat[topH][topW] == tar ? true : false;
        }

        int pivotH = topH + (bottomH - topH) / 2;
        int pivotW = topW + (bottomW - topW) / 2;

        // 因为是均分，并不知道可能出现在哪一个submatrix，所以四个submatrix都要检查
        boolean m1 = helper(topH, topW, pivotH, pivotW);                          // 第二象限
        boolean m2 = helper(topH, pivotW+1, pivotH, bottomW);               // 第一象限
        boolean m3 = helper(pivotH+1, topW, bottomH, pivotW);               // 第三象限
        boolean m4 = helper(pivotH+1, pivotW+1, bottomH, bottomW);    // 第四象限

        return m1 || m2 || m3 || m4;
    }
}

