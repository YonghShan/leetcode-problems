/**
 * @author YonghShan
 * @date 3/13/21 - 11:48
 */
class Solution5 {
    // Binary Count:
    // When the indexes K are written in binary (now indexing from zero), indexes of the second half of a row are ones with the first bit set to 1.
    // This means when applying the algorithm in Approach #4 virtually,
    // the number of times we will flip the final answer is just the number of 1s in the binary representation of K-1.
    /* Runtime: O(log N)  the number of binary bits in N
       Memory: O(1) 是tail recursion，但Java也不会优化为O(1)
     */
    public int kthGrammar(int N, int K) {
        return Integer.bitCount(K - 1) % 2;
    }
}
