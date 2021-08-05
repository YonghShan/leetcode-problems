/**
 * @author YonghShan
 * @date 8/4/21 - 23:17
 */
public class Solution2 {
    // Clever Trick: 利用每一行的最小值和次小值进行优化    推荐
    /* Runtime: 4ms (faster than 68.98%)     O(n^2)
       Memory: 45.1MB (less than 94.04%)     O(n^2)
     */
    int MAX = Integer.MAX_VALUE;
    public int minFallingPathSum(int[][] arr) {
        // Step 1: 定义dp array
        int n = arr.length;
        int[][] f = new int[n][n];

        // i1代表最小值的列下标；i2代表次小值的列下标
        int i1 = -1, i2 = -1;

        // Step 2: 初始化dp array：起点为第一行任一元素；找到第一行的i1和i2。
        for (int i = 0; i < n; i++) {
            // 初始化dp array
            int val = arr[0][i];
            f[0][i] = val;
            // 更新 i1 和 i2
            if (val < (i1 == -1 ? MAX : f[0][i1])) {
                i2 = i1;
                i1 = i;
            } else if (val < (i2 == -1 ? MAX : f[0][i2])) {
                i2 = i;
            }
        }

        // Step 3: 从第二行进行状态转移
        for (int i = 1; i < n; i++) { // 从f的第二行开始更新
            // 每到新的一行，也需要找该行的最小值 ti1 和次小值 ti2
            int ti1 = -1, ti2 = -1;

            for (int j = 0; j < n; j++) {
                f[i][j] = MAX;
                int val = arr[i][j];
                // 更新动规值
                // 可以选择上一行「最小值」的列选择「最小值」i1
                if (j != i1)
                    f[i][j] = f[i - 1][i1] + val;
                    // 不能选择上一行「最小值」的列选择「次小值」i2
                else
                    f[i][j] = f[i - 1][i2] + val;

                // 更新本行的最小值ti1和次小值ti2
                if (f[i][j] < (ti1 == -1 ? MAX : f[i][ti1])) {
                    ti2 = ti1;
                    ti1 = j;
                } else if (f[i][j] < (ti2 == -1 ? MAX : f[i][ti2])) {
                    ti2 = j;
                }
            }
            // 本行遍历结束，利用当前行最小值ti1和次小值ti2更新i1和i2
            i1 = ti1;
            i2 = ti2;
        }

        // Step 4: 取结果：终点为f array最后一行中的最小值，即f[n-1][i1]
        return f[n-1][i1];
    }
}
