/**
 * @author YonghShan
 * @date 3/14/21 - 23:32
 */
class Solution3 {
    // Brute Force
    /* Runtime: O(nm) ≈ O(n^2)
       Memory:  O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }
}
