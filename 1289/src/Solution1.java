/**
 * @author YonghShan
 * @date 8/4/21 - 21:49
 */
public class Solution1 {
    // DP 路径问题 Template
    /* Runtime: 50ms (faster than 30.03%)    O(n^3)
       Memory: 45.5MB (less than 68.73%)     O(n^2)
     */
   public int minFallingPathSum(int[][] arr) {
        // Step 1: 定义dp array
        int n = arr.length;
        int[][] f = new int[n][n];

        // Step 2: 初始化dp array：起点为第一行任一元素
        for (int i = 0; i < n; i++) f[0][i] = arr[0][i];

        // Step 3: 从第一行进行状态转移
        for (int i = 1; i < n; i++) { // 从f的第二行开始更新
            for (int j = 0; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                int val = arr[i][j];
                // 具体根据移动方向的限制而定：非同列（需要枚举上一行除同一列以外的所有列）
                for (int k = 0; k < n; k++) {
                    if (k != j) f[i][j] = Math.min(f[i][j], f[i-1][k] + val);
                }
            }
        }

        // Step 4: 取结果：终点为f array最后一行中的最小值
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) ans = Math.min(ans, f[n-1][i]);
        return ans;
    }
}
