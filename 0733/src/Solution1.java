import java.util.Arrays;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/8/21 - 09:32
 */

class Solution1 {
    // Recursion:
    /* Runtime: 1ms
       Memory: 39.7MB
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
            for (int[] direction : DIRECTIONS) {
                int r = sr + direction[0];
                int c = sc + direction[1];
                if (r < 0 || c < 0 || r >= height || c >= width || image[r][c] != currColor) {
                    continue;
                }
                floodFill(image, r, c, newColor);
            }
        }

        return image;
    }
}
