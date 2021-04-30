/**
 * @author YonghShan
 * @date 4/29/21 - 10:09
 */
public class Solution2 {
    // Newton's Method
    /* Runtime: O(logn)
       Memory: O(1)
     */
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;

        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }
}
