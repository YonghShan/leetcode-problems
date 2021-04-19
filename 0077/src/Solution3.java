import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/18/21 - 20:20
 */
public class Solution3 {
    // Backtracking: 根据Solution2修改的Solution1
    /* Runtime: 20ms
       Memory: 39.8MB
     */
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> rowRes = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1);
        return res;
    }

    public void backtrack(int idx) {
        for (int i = idx; i <= n; i++) {
            rowRes.add(i);
            if (rowRes.size() == k) {
                res.add(new ArrayList(rowRes));
            } else {
                backtrack(i+1);
            }
            rowRes.remove(rowRes.size()-1);
        }
    }
}
