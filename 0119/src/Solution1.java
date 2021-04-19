import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/11/21 - 16:13
 */
class Solution1 {
    // Recursion
    /* Runtime: O(2^rowIndex) when rowIndex >= 10, TLE
       Memory:  O(k)+O(k)≃O(k): We need O(k) space to store the output of the k_th row;
                                At worst, the recursive call stack has a maximum of k calls in memory, each call taking constant space.
                                That's O(k) worst case recursive call stack space.
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 1; i < rowIndex; i++) {
            res.add(getRow(rowIndex-1).get(i-1) + getRow(rowIndex-1).get(i));  // 这里需要计算两次getRow(rowIndex-1)
        }
        if (rowIndex > 0) res.add(1);
        return res;
    }
}

