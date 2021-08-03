/**
 * @author YonghShan
 * @date 8/3/21 - 01:24
 */
public class Solution1 {
    // 被[0064][0120]限制了思维
    /* Runtime: 29ms (faster than 5.05%)   O(n^3)
       Memory: 39.6MB (less than 64.12%)   O(n^2)
     */
    int MAX = Integer.MAX_VALUE;
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int ans = MAX;
        // 枚举第一行的元素依次作为起点
        for (int i = 0; i < n; i++) ans = Math.min(ans, find(matrix, i));
        return ans;
    }

    public int find(int[][] matrix, int idx) {
        int n = matrix.length;
        int ans = MAX;
        int[][] f = new int[n][n];
        // f矩阵的第一行除了本次寻找的起点为原矩阵matrix中的对应值，其余全置为MAX
        for (int i = 0; i < n; i++) f[0][i] = i == idx ? matrix[0][i] : MAX;
        for (int i = 1; i < n; i++) { // 第0行只有一个元素
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                f[i][j] = MAX;
                // 下面if判断中，与MAX比较，是防止其值为MAX的情况下再加val导致overflow
                if (f[i - 1][j] != MAX) f[i][j] = f[i - 1][j] + val; // 由上方移动得来
                if (j - 1 >= 0 && f[i - 1][j - 1] != MAX) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + val); // 由左上方移动得来
                if (j + 1 < n && f[i - 1][j + 1] != MAX) f[i][j] = Math.min(f[i][j], f[i - 1][j + 1] + val); // 由右上方移动得来
            }
        }
        // 到此，f矩阵已填完所有值。遍历f矩阵的最后一行找到最小值即为答案
        for (int i = 0; i < n; i++) ans = Math.min(ans, f[n - 1][i]);
        return ans;
    }
}
