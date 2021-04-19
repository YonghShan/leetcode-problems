/**
 * @author YonghShan
 * @date 3/12/21 - 23:50
 */
public class Solution1 {
    // Recursion: 注意N和K都是1-indexed
    // 没法Memoization，每层只走一次
    /* Runtime: 0ms     O(N)
       Memory: 35.9MB   O(N)
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        if (kthGrammar(N-1, ((K+1)/2)) == 0) {
            return (K % 2 == 1) ? 0 : 1;
        } else {
            return (K % 2 == 1) ? 1 : 0;
        }
    }
}
