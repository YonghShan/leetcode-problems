/**
 * @author YonghShan
 * @date 4/29/21 - 09:54
 */
public class Solution1 {
    // Binary Search
    /* Runtime: 0ms                         O(logn)
       Memory: 35.4MB (less than 85.62%)    O(1)
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        long left = 1;
        long right = num / 2;
        while (left <= right) {                     // 仅仅将tmp设为long不行，还需要将mid设为long，因此left和right也都要设为long
            long mid = left + (right - left) / 2;   // mid如果不设为long，会影响tmp的计算结果：int mid=202050 => long tmp=-2125470460
            long tmp = mid * mid;  // tmp是一定需要设为long的
            if (tmp == num) {
                return true;
            } else if (tmp < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
