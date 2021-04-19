import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/11/21 - 16:33
 */
public class Solution1Advanced {
    // Recursion: won't be TLE
    /* Runtime: 0ms    O(rowIndex^2) ?
       Memory: 36.5MB
     */
    private List<Integer> prev;
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex > 1) {
            prev = getRow(rowIndex-1);  // 提前取好getRow(rowIndex-1)
        }

        List<Integer> res = new ArrayList<>();
        res.add(1);

        for (int i = 1; i < rowIndex; i++) {
            res.add(prev.get(i-1) + prev.get(i));
        }
        if (rowIndex > 0) res.add(1);
        return res;
    }
}
