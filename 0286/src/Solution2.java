import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/22/21 - 15:39
 */
public class Solution2 {
    // 从每个GATE出发，将其上下左右四个邻居的值改为1，再为四个邻居各自的邻居们设定距离（本身值加1）
    /* Runtime: 8ms    O(mn)
       Memory: 42.8MB  O(mn)
     */
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList( // 上下左右四个邻居
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) { // 将所有的GATES放入q，作为BFS的起点
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) { // rooms[r][c] != EMPTY 这里不仅是排除非room的情况，也是在判断该room是否已经visited
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1; // 更新每个room的值
                q.add(new int[] { r, c });
            }
        }
    }
}
