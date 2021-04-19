import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/11/21 - 23:21
 */
public class Solution1Advanced {
    // Recursion with Memoization
    /* Runtime: 0ms      O(n)
       Memory: 35.9MB    O(n)
     */
    private HashMap<Integer, Integer> cache = new HashMap<>();
    public int fib(int n) {
        if (cache.containsKey(n)) return cache.get(n);

        int res;
        if (n <= 1) {
            res = n;
        } else {
            res = fib(n-1) + fib(n-2);
        }
        cache.put(n, res);
        return res;
    }
}
