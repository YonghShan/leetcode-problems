import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/11/21 - 16:29
 */
public class Solution2 {
    // 官方Recursion: Time Complexity and Space Complexity is same as Solution1   不用看了，没有Solution1Advanced好
    // when rowIndex >= 30, TLE
    private int getNum(int row, int col) {
        if (row == 0 || col == 0 || row == col) {
            return 1;
        }

        return getNum(row - 1, col - 1) + getNum(row - 1, col); // 同样是对同一行计算了两次
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            ans.add(getNum(rowIndex, i));
        }

        return ans;
    }
}
