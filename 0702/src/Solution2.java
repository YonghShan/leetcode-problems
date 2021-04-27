/**
 * @author YonghShan
 * @date 4/26/21 - 22:44
 */
public class Solution2 {
    // Binary Search
    /* Runtime: 1ms      O(logn) = O(log10^4) = O(4log10) = O(1)
       Memory: 39.9MB    O(1)
     */
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 10000;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == 2147483647 || reader.get(mid) > target) {
                right = mid - 1;
            } else if (reader.get(mid) == target) {
                return mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
