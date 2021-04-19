import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/12/21 - 15:57
 */
public class Solution1 {
    // Recursion with Memoization
    // 会StackOverflow
    // Memoization加不加没区别！！！！
    private HashMap<Double, HashMap<Integer, Double>> memo = new HashMap<>();
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (memo.containsKey(x)) {
            HashMap<Integer, Double> map = memo.get(x);
            if (map.containsKey(n)) return map.get(n);
        }
        double res;
        if (n < 0) {
            res = 1 / (x*myPow(x, -n-1));
        } else {
            res = x*myPow(x, n-1);
        }
        HashMap<Integer, Double> m = new HashMap<>();
        m.put(n, res);
        memo.put(x, m);
        return res;
    }
}
