import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/8/21 - 10:11
 */
public class Solution3 {
    // BFS
    /* Runtime: 2ms
       Memory: 40MB
     */
    private static final List<int[]> DIRECTIONS = Arrays.asList( // 上下左右四个邻居
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{0, -1}
    );

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int height = image.length;
        int width = image[0].length;

        int currColor = image[sr][sc];
        if (currColor != newColor) {   // 防止currColor和newColor一样，导致陷入死循环
            image[sr][sc] = newColor;
            Queue<int[]> neighbors = new LinkedList<>();
            neighbors.add(new int[]{sr, sc});
            while (!neighbors.isEmpty()) {
                int[] points = neighbors.poll();
                int row = points[0];
                int clo = points[1];
                for (int[] direction : DIRECTIONS) {
                    int r = row + direction[0];
                    int c = clo + direction[1];
                    if (r < 0 || c < 0 || r >= height || c >= width || image[r][c] != currColor) {
                        continue;
                    }
                    image[r][c] = newColor;
                    neighbors.add(new int[]{r, c});
                }
            }
        }
        return image;
    }
}
