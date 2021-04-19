import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/13/21 - 15:03
 */
public class Solution1 {
    // Recursion with Memoization
    /* Runtime: 0ms     (?) O(n^2) 如果没有memoization的话，O(n^n)
       Memory: 36.1MB       O(n)
     */
    private HashMap<Integer, Integer> memo = new HashMap<>() {
        {
            put(0,0); // n=0时，有0种结构
            put(1,1); // n=1时，有1种结构
        }
    };
    public int numTrees(int n) {
        if (memo.containsKey(n)) return memo.get(n);

        int res = 0;
        for (int i = 1; i < n+1; i++) { // 从第一个数开始作为root
            // 判断root左边的数的个数能有多少种结构
            int left = 0;
            if (memo.containsKey(i - 1)) {
                left += memo.get(i - 1);
            } else {
                left += numTrees(i - 1);
            }
            // 判断root右边的数的个数能有多少种结构
            int right = 0;
            if (memo.containsKey(n - i)) {
                right += memo.get(n - i);
            } else {
                right += numTrees(n - i);
            }
            // 以第i个数为root共有多少种结构：left*right，注意left或right为0的情况
            // 累加前i个数所有的组合
            if (left != 0 && right != 0) {
                res += left * right;
            } else if (left == 0) {
                res += right;
            } else {
                res += left;
            }
        }
        memo.put(n, res);
        return res;
    }
}
