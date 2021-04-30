/**
 * @author YonghShan
 * @date 4/29/21 - 10:25
 */
public class Solution1 {
    // Binary Search Template II
    /* Runtime: 0ms                         O(logn)
       Memory: 39.3MB (less than 43.94%)    O(1)
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        if (target < letters[0] || target >= letters[len-1]) return letters[0];
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < letters[mid]) {
                right = mid; // 要选出比target大的，此时mid仍有可能为最终答案
            } else {
                left = mid + 1;
            }
        }

        return letters[left];
    }
}
