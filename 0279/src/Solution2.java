import java.util.Arrays;

/**
 * @author YonghShan
 * @date 2/25/21 - 00:02
 */
class Solution2 {
    // DP: numSquares(n) = min(numSquares(n-k)) + 1, ∀ k ∈ {square numbers}
    // e.g. n = 13时， k ∈ {1, 4, 9} => numSquares(13) = min(numSquares(13-1)，numSquares(13-4)，numSquares(13-9)) + 1
    //                                                = min(numSquares(12)，numSquares(9)，numSquares(4)) + 1
    // 因为Solution1对于中间答案的重复计算，用dp数组存储中间答案
    /* Runtime: 40ms      O(n*sqrt(n)): O(n)的for loop里嵌套O(sqrt(n))的for loop
       Memory: 38.8MB     O(n)
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;

        int len = (int) Math.sqrt(n);
        int[] perfectSquares = new int[len]; // sqrt(13) = 3.6, int取3， 小于13的perfect square有3个
        for (int i = 0; i < len; i++) perfectSquares[i] = (i + 1) * (i + 1);
        //System.out.println(Arrays.toString(perfectSquares));

        for (int i = 1; i < n+1; i++) {      // O(n)
            int jLen = (int) Math.sqrt(i);
            int min = n; // 每计算一个新的n的dp，要重新置min，不然min还是上一个数的min，导致dp[]更新不了
            for (int j = 0; j < jLen; j++) {   // O(sqrt(n))
                min = Math.min(min, dp[i-perfectSquares[j]]);
            }
            dp[i] = min+1;
        }
        //System.out.println(Arrays.toString(dp));

        return dp[n];
    }
}
