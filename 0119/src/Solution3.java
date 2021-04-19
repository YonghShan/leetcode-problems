import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YonghShan
 * @date 3/11/21 - 21:47
 */
public class Solution3 {
    // DP with 2D array
    /* Runtime: 1ms     O(rowIndex^2)
       Memory: 36.6MB   O(rowIndex^2)
     */
    public List<Integer> getRow(int rowIndex) {
        // 这里dp并没有像Solution4是Integer[]，而为了保持初始值为0，选择int[]
        // Integer[]的初始值为null
        int[][] dp = new int[rowIndex+1][rowIndex+1]; // 是从第0行开始的，所以宽度为rowIndex+1
        // 将每行行首赋为1
        for (int i = 0; i < rowIndex+1; i++) {
            dp[i][0] = 1;
        }
        // 更新：
        for (int i = 1; i < dp.length; i++){ // 从第二行开始更新
            for (int j = 1; j < rowIndex+1; j++)
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
        }
        // 将int[]转为List
        List<Integer> res = Arrays.stream(dp[rowIndex]).boxed().collect(Collectors.toList()); // 也可以在上面更新时当j=rowIndex时，将dp[i][j]存入List

        return res;
    }
}
