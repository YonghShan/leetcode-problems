import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/10/21 - 23:16
 */
public class Solution2 {
    // Recursion:
    /* Runtime: 2485ms
       Memory: 36.8MB
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < numRows+1; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 1; j < i+1; j++) {
                row.add(helper(i, j));
            }
            res.add(row);
        }
        return res;
    }

    public int helper(int i, int j) {
        if (j == 1 || j == i) {
            return 1;
        }
        return helper(i-1, j-1) + helper(i-1, j);
    }
}
