/**
 * @author YonghShan
 * @date 1/29/21 - 23:39
 */

class Solution1 {
    // Dynamic Programming but not in-place
    /* Runtime: 1ms
       Memory: 40M
     */
    public int[] replaceElements(int[] arr) {
        int len = arr.length;
        int[] dp = new int[len];

        for (int i=len-1; i>=0; i--) {
            if (i == len-1) {
                dp[i] = -1;
            } else {
                dp[i] = Math.max(arr[i+1], dp[i+1]);
            }
        }

        return dp;
    }
}
