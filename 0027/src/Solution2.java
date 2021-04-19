/**
 * @author YonghShan
 * @date 1/28/21 - 00:50
 */

class Solution2 {
    // Two pointers: the elements before pointer i must be safe (not equal to val), so i is the length of the result array
    // Pointer j is to find the first safe element which need to be moved into the safe part
    /* Runtime: 0ms
       Memory: 38M
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) { // find the safe element
                nums[i] = nums[j]; // move it into the end of the safe part
                i++; // i redirects to the new boundary of the safe part
            } // only in this case can i be changed!!!
        }
        return i;
    }
}