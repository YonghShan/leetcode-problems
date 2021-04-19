import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/8/21 - 11:08
 */
public class Solution1 {
    // 从每个0出发，向外更新距离
    /* Runtime: 25ms     O(r⋅c)
       Memory: 42.3MB    O(r⋅c). Additional O(r⋅c) for queue.
     */
    private static final List<int[]> DIRECTIONS = Arrays.asList( // 上下左右四个邻居
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    public int[][] updateMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        // 记录最短距离的2D数组：
        int[][] res = new int[height][width];
        for (int i = 0; i < height; i++) {     // 给res赋初值： Arrays.fill()只能填充一维数组，而且内部依旧是用for循环实现的，并不能提高赋值的效率
            for (int j = 0; j < width; j++) {
                res[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        // 找matrix中所有的0：
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
                if (matrix[i][j] == 0) {
                    q.add(new int[]{i, j});
                    res[i][j] = 0;
                }
            }
        }
        // BFS:
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int r = temp[0];
            int c = temp[1];
            for (int[] direction : DIRECTIONS) {
                int row = r + direction[0];
                int clo = c + direction[1];
                if (row < 0 || row >= height || clo < 0 || clo >= width || res[row][clo] == 0) {
                    continue;
                }
                if (res[row][clo] > res[r][c] + 1) {  // 对于res[row][clo]的赋值，不能简单地：res[row][clo] = res[r][c] + 1;
                    res[row][clo] = res[r][c] + 1;    // 会导致重复赋值
                    q.add(new int[]{row, clo});
                }
            }
        }

        return res;
    }
}
