import java.util.HashMap;
import java.util.HashSet;

/**
 * @author YonghShan
 * @date 3/12/21 - 13:48
 */
public class Solution1 {
    // 更多方法参考509：n级台阶的上法数对应第n+1个Fibonacci Number
    // Recursion with Memoization: 如果不memoization，会TLE when n >= 45
    /* Runtime: 0ms
       Memory: 35.7MB
     */
    private HashMap<Integer, Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
        if (memo.containsKey(n)) return memo.get(n);

        int res;
        if (n == 0 || n == 1 || n == 2) {
            res = n;
        } else {
            res = climbStairs(n-2)  + climbStairs(n-1);
        }
        memo.put(n, res);
        return res;
    }
}
