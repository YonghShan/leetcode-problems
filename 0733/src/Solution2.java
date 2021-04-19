import java.util.Arrays;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/8/21 - 09:57
 */
public class Solution2 {
    // DFS
    /* Runtime: 1ms     O(n), where n is the number of pixels in the image
       Memory: 39.8MB   O(n)
     */
    private static final List<int[]> DIRECTIONS = Arrays.asList( // 上下左右四个邻居
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{0, -1}
    );

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor != newColor) dfs(image, sr, sc, currColor, newColor);
        return image;
    }

    public void dfs(int[][] image, int r, int c, int currColor, int newColor) {
        int height = image.length;
        int width = image[0].length;
        image[r][c] = newColor;

        for (int[] direction : DIRECTIONS) {
            int row = r + direction[0];
            int column = c + direction[1];
            if (row < 0 || column < 0 || row >= height || column >= width || image[row][column] != currColor) {
                continue;
            }
            dfs(image, row, column, currColor, newColor);
        }
    }
}
