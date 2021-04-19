/**
 * @author YonghShan
 * @date 1/29/21 - 22:21
 */

class Solution2 {
    // Let's walk up from left to right until we can't: that has to be the peak.
    // We should ensure the peak is not the first or last element.
    // Then, we walk down. If we reach the end, the array is valid, otherwise its not.
    /* Runtime: 1ms
       Memory: 40M
     */
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        int len = arr.length;

        // walk up
        while (i < len-1 && arr[i] < arr[i+1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == len-1)
            return false;

        // walk down
        while (i < len-1 && arr[i] > arr[i+1])
            i++;

        return i == len-1;
    }
}
