import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/19/21 - 10:28
 */
class Solution2 {
    // Backtracking: 官方
    /* Runtime: O(4^n / sqrt(n))   it turns out this is the n-th Catalan number 1/(n+1) (2n, n), which is bounded asymptotically by 4^n / sqrt(n)
       Memory:  O(4^n / sqrt(n)), as described above, and using O(n) space to store the sequence.
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}
