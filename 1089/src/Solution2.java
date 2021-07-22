/**
 * @author YonghShan
 * @date 7/22/21 - 00:09
 */
public class Solution2 {
    // Two Pass：先通过扫描数组中0的个数，确认还会出现在modified array中的元素范围；然后开始从后向前重构array
    /* Runtime: 0ms                        O(n)
       Memory: 39.3MB (less than 44.42%)   O(1)
     */
    public void duplicateZeros(int[] arr) {
        int possibleDups = 0;
        int len = arr.length - 1;

        // Find the number of zeros to be duplicated
        // Stopping when left points beyond the last element in the original array
        // which would be part of the modified array
        for (int left = 0; left <= len - possibleDups; left++) {
            if (arr[left] == 0) {
                // Edge case: This zero can't be duplicated. We have no more space,
                // as left is pointing to the last element which could be included
                if (left == len - possibleDups) {
                    // For this zero we just copy it without duplication.
                    arr[len] = 0;
                    len -= 1;
                    break;
                }
                possibleDups++;
            }
        }

        // Start backwards from the last element which would be part of new array.
        int last = len - possibleDups;
        // Copy zero twice, and non zero once.
        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + possibleDups] = 0;
                possibleDups--;
                arr[i + possibleDups] = 0;
            } else {
                arr[i + possibleDups] = arr[i];
            }
        }
    }
}
