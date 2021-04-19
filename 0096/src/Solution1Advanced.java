import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/13/21 - 16:24
 */
public class Solution1Advanced {
    // Recursion with Memoization
    /* Runtime: 0ms
       Memory: 35.6MB (less than 80.75%)
     */
    private HashMap<Integer, Integer> memo = new HashMap<>() {
        {
            put(0,1); // n=0时，视为有1种结构，方便之后计算
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
            // 以第i个数为root共有多少种结构：left*right
            // 累加前i个数所有的组合
            res += left * right;
        }
        memo.put(n, res);
        return res;
    }
}
