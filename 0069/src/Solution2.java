/**
 * @author YonghShan
 * @date 4/17/21 - 23:32
 */
class Solution2 {
    // sqrt(x) = e ^ (1/2 * logx)): This is how the calculators in real life works.
    /* Runtime: O(1)
       Memory: O(1)
     */
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }
}
