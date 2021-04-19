/**
 * @author YonghShan
 * @date 3/13/21 - 11:21
 */
class Solution2 {
    // Brute Force
    /* Runtime: O(2^N)
       Memory: O(2^N)
     */
    public int kthGrammar(int N, int K) {
        int[] lastrow = new int[1 << N]; // lastrow.lenght = 2^N;
        for (int i = 1; i < N; ++i) {
            for (int j = (1 << (i-1)) - 1; j >= 0; --j) {
                lastrow[2*j] = lastrow[j];
                lastrow[2*j+1] = 1 - lastrow[j];
            }
        }
        return lastrow[K-1];
    }
}
