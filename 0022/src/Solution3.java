import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/19/21 - 10:38
 */
class Solution3 {
    // Closure Number: 基本的基于卡塔兰数的recursion，包含了大量duplicate calculation，可以采用memoization或者DP优化
    /* Runtime: O(4^n / sqrt(n))
       Memory:  O(4^n / sqrt(n))
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            // 利用的是卡塔兰数的递推关系：C(0) = 1 and C(n) = \sum_{i=0}^(n-1) C(i)C(n-1-i), for n >= 0
            for (int c = 0; c < n; ++c)                                  // \sum_{i=0}^(n-1)
                for (String left: generateParenthesis(c))                // C(i)
                    for (String right: generateParenthesis(n-1-c))    // C(n-1-i)
                        // Let the "(" always at the first position, to produce a valid result,
                        // we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}
