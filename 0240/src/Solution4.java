/**
 * @author YonghShan
 * @date 3/14/21 - 23:34
 */
public class Solution4 {
    // Binary Search：比如matrix高度为3，宽度为5: 当前较短的是高度，则遍历3次，每次对一行进行binary search，binary search需要的time是O(log5)
    // 这样做的原因是3*log5 (≈7) < 5*log3 (≈8)
    // 当n ≈ m时，O(nlogm) ≈ O(nlogn)
    /* Runtime: 7ms (faster than 35.94%)     O(nlogm), where n is Math.min(matrix.length, matrix[0].length), m is the larger one.
       Memory: 44.4MB (less than 89.00%)     O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean isVertical = false;
        int shorterDim = 0;
        if (matrix.length > matrix[0].length) {
            isVertical = true;
            shorterDim = matrix[0].length;
        } else {
            isVertical = false;
            shorterDim = matrix.length;
        }

        for (int i = 0; i < shorterDim; i++) {  // 比如matrix高度为3，宽度为5: 当前较短的是高度，则遍历3次，每次对一行进行binary search
            boolean res = isVertical ? binarySearch(matrix, target, i, true) : binarySearch(matrix, target, i, false);
            if (res == true) return true;
        }

        return false;
    }

    public boolean binarySearch(int[][] matrix, int target, int start, boolean isVertical) {
        int low = 0;
        int high = isVertical ? matrix.length-1 : matrix[0].length-1;

        while (high >= low) {
            int mid = (high + low) / 2;
            if (isVertical) {
                if (matrix[mid][start] < target) {  // target might be in the down side
                    low = mid + 1;
                } else if (matrix[mid][start] > target) {  // target might be in the above side
                    high = mid - 1;
                } else {   // matrix[start][mid] is the target
                    return true;
                }
            } else {
                if (matrix[start][mid] < target) {  // target might be in the right side
                    low = mid + 1;
                } else if (matrix[start][mid] > target) {  // target might be in the left side
                    high = mid - 1;
                } else {   // matrix[start][mid] is the target
                    return true;
                }
            }
        }
        return false;
    }
}
