import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/18/21 - 23:35
 */
public class Solution1 {
    // Backtracking: 和[0077]官方做法类似
    /* Runtime: 1ms (faster than 84.43%)    给定n组括号，则限定了生成树的max depth为2n，
                                            则Time Complexity = all the number of nodes in the tree
                                                              = 2^0 + 2^1 + ... + 2^(2n-1)
                                                              = 2^(2n) - 1
                                            => Time Complexity = O(2^(2n) - 1) = O(2^(2n)) = O(2^n)    更精确的upper bound见后
       Memory: 38.8MB (less than 96.82%)
     */
    private List<String> res = new ArrayList<>();
    private int left; // the number of left parentheses
    private int right; // the number of right parentheses
    private int n;
    public List<String> generateParenthesis(int n) {
        this.n = n;
        backtrack(1, new StringBuilder());
        return res;
    }

    public void backtrack(int start, StringBuilder curr) {
        if (curr.length() == 2 * n) res.add(new String(curr.toString()));

        for (int i = 0; i < 2; i++) { // 每个位置只有两种可能：0 = "(" or 1 = ")"
            String s = i == 0 ? "(" : ")";
            if (isValid(start, s)) {
                if (s == "(") {
                    left++;
                } else {
                    right++;
                }
                curr.append(s);
                backtrack(start+1, curr);
                if (s == "(") {
                    left--;
                } else {
                    right--;
                }
                curr.deleteCharAt(curr.length()-1);
            }
        }
    }

    public boolean isValid(int idx, String parenthesis) {
        if (parenthesis == "(" && left == n) return false;
        if (parenthesis == ")" && right == n) return false;
        if (parenthesis == ")" && left == right) return false;
        if (parenthesis == ")" && idx == 0) return false;
        return true;
    }
}
