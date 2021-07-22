/**
 * @author YonghShan
 * @date 1/27/21 - 15:42
 */

class Solution1 {
    // Brute Force
    /* Runtime: 19ms (faster than 14.04%)    O(n^2)
       Memory: 38.8MB (less than 91.88%)     O(1)
     */
    public void duplicateZeros(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == 0) {
                for (int j=arr.length-1; j>i; j--) {
                    arr[j] = arr[j-1];
                }
                i++;
            }
        }
    }
}
