import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/10/21 - 12:20
 */
public class Solution1 {
    // Iteration:
    /* Runtime: 0ms
       Memory: 36.7MB
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < numRows+1; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < i-1; j++) {
                row.add(res.get(i-2).get(j-1)+res.get(i-2).get(j));
            }
            if (i > 1) row.add(1);
            res.add(row);
        }
        return res;
    }
}
