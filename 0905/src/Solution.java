/**
 * @author YonghShan
 * @date 1/30/21 - 21:04
 */

class Solution {
    // Two Pointers
    /* Runtime: 1ms
       Memory: 40M
     */
    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        for (int j = 0; j < A.length; j++) {
            if (A[j] %2 == 0) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        return A;
    }
}