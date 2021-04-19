import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/22/21 - 21:25
 */
public class Solution2 {
    // BFS with Iteration:
    // 从grid[0][0]开始按照row-wise在grid中找'1'，找到的第一个'1'作为island/root向上下左右四个方向作BFS扩张
    // 并将其的内容置为'v'表示visited，一直到无'1'可走
    // 继续在grid中按照row-wise找下一个'1'作为新的island的root
    /* Runtime: 9ms (faster than 5.8%)    O(M×N) where MM is the number of rows and NN is the number of columns.
       Memory: 46.9MB (less than 5.09%)   O(min(M,N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
                                          看笔记画出来的tree, 无论是哪种构建树的方式，在树的同一level最多只有min(M,N)个nodes，所以即使是worst case，Queue中也只存了min(M,N)个nodes
     */
    private static final List<int[]> DIRECTIONS = Arrays.asList( // 上下左右四个邻居
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{0, -1}
    );

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int height = grid.length;
        int width = grid[0].length;
        int nums = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '1') { // 找到一个值为1的land作为root，island的数量+1，并向外扩张
                    nums++;
                    grid[i][j] = 'v';
                    // BFS
                    Queue<int[]> neighbors = new LinkedList<>();
                    neighbors.add(new int[]{i, j});
                    while (!neighbors.isEmpty()) {
                        int[] points = neighbors.poll();
                        int row = points[0];
                        int clo = points[1];
                        for (int[] direction : DIRECTIONS) {
                            int r = row + direction[0];
                            int c = clo + direction[1];
                            if (r < 0 || c < 0 || r >= height || c >= width || grid[r][c] == '0' || grid[r][c] == 'v') {
                                continue;
                            }
                            grid[r][c] = 'v';
                            neighbors.add(new int[]{r, c});
                        }
                    }
                }
            }
        }
        return nums;
    }
}
