/**
 * @author YonghShan
 * @date 4/18/21 - 23:35
 */

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version);
 */

public class Solution extends VersionControl {
    // Binary Search Template II
    /* Runtime: 12ms (faster than 97.73%)
       Memory: 35.6MB (less than 69.87%)
     */
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid) == true) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
