/**
 * @author YonghShan
 * @date 3/13/21 - 11:43
 */
class Solution4 {
    // Recursion: Flip Variant
    // the second half is always the first half "flipped": namely, that '0' becomes '1' and '1' becomes '0'.
    // if K is in the second half, then we could put K -= (1 << N-2) so that it is in the first half, and flip the final answer.
    /* Runtime: O(N)
       Memory: O(N) 是tail recursion，但Java也不会优化为O(1)
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        if (K <= 1 << N - 2)   // K在first half
            return kthGrammar(N - 1, K);
        return kthGrammar(N - 1, K - (1 << N - 2)) ^ 1;   // K在second half，则对K -= (1 << N-2)，得到的结果再flip（和1异或）
    }
}

