/**
 * @author YonghShan
 * @date 2/22/21 - 16:42
 */
public class Solution1 {
    // DFS with Recursion: 注意这里并不只将grid看成binary tree，因为相邻'1'的分布不能仅仅搜寻某两个方向的值，而是上下左右四个方向都要搜寻
    // 从grid[0][0]开始按照row-wise在grid中找'1'，找到的第一个'1'作为island/root向上下左右四个方向作DFS扩张
    // 并将其的内容置为'v'表示visited，一直到无'1'可走
    // 继续在grid中按照row-wise找下一个'1'作为新的island的root
    /* Runtime: 1ms (faster than 100%)    O(M×N) where MM is the number of rows and NN is the number of columns.
       Memory: 44.6MB (less than 11.09%)  worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int height = grid.length;
        int width = grid[0].length;
        int nums = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '1') { // 找到一个值为1的land作为root，island的数量+1，并向外扩张
                    nums++;
                    dfs(grid, i, j);
                }
            }
        }
        return nums;
    }

    public void dfs(char[][] grid, int row, int clo) {
        // 相邻'1'的分布不能仅仅搜寻某两个方向的值，而是上下左右四个方向都要搜寻
        int height = grid.length;
        int width = grid[0].length;

        if (row >= height || row < 0 || clo >= width || clo < 0 || grid[row][clo] == '0' || grid[row][clo] == 'v') return;
        grid[row][clo] = 'v';
        dfs(grid, row, clo+1);
        dfs(grid, row, clo-1);
        dfs(grid, row+1, clo);
        dfs(grid, row-1, clo);
    }
}
