/**
 * @author YonghShan
 * @date 4/17/21 - 23:30
 */
public class Solution1 {
    // For x ≥ 2 the square root is always smaller than x/2 and larger than 0 : 0<a<x/2.
    /* Runtime: 2ms (faster than 9.93%)     O(logn)
       Memory: 37.9MB (less than 5.61% )    O(1)
     */
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = 0;
        int right = x/2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long tmp = (long) mid * mid;
            if (tmp > x) {
                right = mid - 1;
            } else if (tmp < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return right; // 当while结束时，left肯定是大于right，而题目要求对于sqrt(8)=2.82..., 要返回2而不是3，所以这里return right
    }
}
