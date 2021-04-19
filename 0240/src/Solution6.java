/**
 * @author YonghShan
 * @date 3/15/21 - 16:15
 */
class Solution6 {
    // 从左下角开始，如果当前值比target大，则向上；如果当前值比target小，则向右
    // 从右上角开始也可以，但是另外两个角不行
    /* Runtime: O(n+m)
       Memory: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }
}
