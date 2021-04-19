/**
 * @author YonghShan
 * @date 1/30/21 - 00:26
 */

class Solution3 {
    // in-place but fancier
    /* Runtime: 1ms
       Memory: 40M
     */
    public int[] replaceElements(int[] arr) {
        int len = arr.length;
        int max = -1;
        for (int i=len-1; i>=0; i--) {
            int temp = arr[i]; // KEY!!!
            arr[i] = max;
            max = Math.max(temp, max);
        }

        return arr;
    }
}
