import java.util.Arrays;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/11/21 - 21:31
 */
public class Solution4 {
    // Memory-efficient DP: with 1D array 滚动数组
    /* Runtime: 0ms       O(rowIndex^2)
       Memory: 36.8MB     O(rowIndex)
     */
    public List<Integer> getRow(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1]; // 这里dp是Integer[]，方便之后转为list！
        Arrays.fill(dp,1);
        for(int i = 2;i < dp.length;i++){ // 从第三行开始更新
            for(int j = i - 1;j > 0;j--) // 需要依据前面的值来更新自己，所以要倒着更新
                dp[j] = dp[j] + dp[j - 1];
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }
}
