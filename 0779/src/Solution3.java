/**
 * @author YonghShan
 * @date 3/13/21 - 11:37
 */
class Solution3 {
    // Recursion: Parent Variant 和我写的Solution1一样
    // In general, the Kth digit's parent is going to be (K+1) / 2.
    // If the parent is 0, then the digit will be the same as 1 - (K%2) (i.e. K%2==1，此时return 0；K%2==0，此时return 1).
    // If the parent is 1, the digit will be the opposite, ie. K%2. (i.e. K%2==1，此时return 1；K%2==0，此时return 0).
    /* Runtime: O(N)
       Memory: O(N) 不是tail recursion，就算是Java也不会优化为O(1)
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        return (~K & 1) ^ kthGrammar(N-1, (K+1)/2); // 先对K按位取反，再和1与，最后和parent异或（相同为0，不同为1）
    }
}
