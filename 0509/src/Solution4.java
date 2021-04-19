/**
 * @author YonghShan
 * @date 3/11/21 - 23:59
 */
class Solution {
    // F(n) / F(n-1) 约等于黄金比例（1+sqrt(5))/2)
    // Time complexity of Math.pow(a,b)不确定，有说是O(1)（input较小时），有说是O(log2(b))（input较大时）
    /* Runtime: O(1), 这题限制了n的范围不超过30
       Memory: O(1)
     */
    public int fib(int N) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, N)/ Math.sqrt(5));
    }
}
