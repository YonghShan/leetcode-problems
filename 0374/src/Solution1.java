/**
 * @author YonghShan
 * @date 4/18/21 - 16:06
 */

/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    // Binary Search
    /* Runtime: 0ms     O(log_2 n)
       Memory: 37.7MB   O(1)
     */
    public int guessNumber(int n) {
        if (n == 1) return 1;

        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
