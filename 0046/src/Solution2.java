import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/20/21 - 11:08
 */
public class Solution2 {
    // Backtracking:
    /* Runtime: 3ms (faster than 22.63%)
       Memory: 41.5MB (less than 9.35%)
     */
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> rowRes = new ArrayList<>();
    private int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        backtrack(0);
        return res;
    }

    public void backtrack(int idx) {
        for (int i = 0; i < nums.length; i++) {
            if (!rowRes.contains(nums[i])) {
                rowRes.add(nums[i]);
                if (idx == nums.length-1) {
                    res.add(new ArrayList<>(rowRes));
                } else {
                    backtrack(idx+1);
                }
                rowRes.remove(rowRes.size()-1);
            }
        }
    }
}