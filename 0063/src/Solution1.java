/**
 * @author YonghShan
 * @date 7/29/21 - 16:37
 */
public class Solution1 {
    // DP
    /* Runtime: 0ms                        O(m x n)
       Memory: 38.2MB (less than 58.25%)   O(m x n)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1; // 题目说了机器人初始位于top-left conner，test case还把obstacleGrid[0][0]有可能是障碍物，无语。。。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对于obstacleGrid[i][j]为1的f[i][j]，不需要更新，初始即为1
                if (obstacleGrid[i][j] != 1) {
                    if (i > 0 && j > 0) {
                        f[i][j] = f[i][j-1] + f[i-1][j];
                    } else if (j > 0) {
                        f[i][j] = f[i][j-1];
                    } else if (i > 0) {
                        f[i][j] = f[i-1][j];
                    }
                }
            }
        }
        return f[m-1][n-1];
    }
}
