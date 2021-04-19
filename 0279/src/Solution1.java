/**
 * @author YonghShan
 * @date 2/24/21 - 23:40
 */
class Solution1 {
    // Recursion: numSquares(n) = min(numSquares(n-k)) + 1, ∀ k ∈ {square numbers}
    // e.g. n = 13时， k ∈ {1, 4, 9} => numSquares(13) = min(numSquares(13-1)，numSquares(13-4)，numSquares(13-9)) + 1
    //                                                = min(numSquares(12)，numSquares(9)，numSquares(4)) + 1
    /* Runtime: 当n>=55时，要么Stack Overflow，要么TLE：每次换一个新n，就要重复计算之前的numSquares
       Memory:
     */
    public int numSquares(int n) {
        if (n == 0) return 0;

        int len = (int) Math.sqrt(n);
        int[] perfectSquares = new int[len]; // sqrt(13) = 3.6, int取3， 小于13的perfect square有3个
        int min = n;
        for (int i = 0; i < len; i++) {
            perfectSquares[i] = (i + 1) * (i + 1);
            min = Math.min(min, numSquares(n - perfectSquares[i]));
        }

        return min+1;
    }
}
