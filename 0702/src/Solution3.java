/**
 * @author YonghShan
 * @date 4/28/21 - 23:45
 */
public class Solution3 {
    // kind of dumb compared to Solution3Advanced
    /* Runtime: O(logT), where TT is an index of target value.
       Memory: O(1)
     */
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) return 0;

        // search boundaries
        int left = 0, right = 1;
        while (reader.get(right) < target) {
            left = right;
            right <<= 1;   // right的取值不断翻倍
        }

        // binary search
        int pivot, num;
        while (left <= right) {
            pivot = left + ((right - left) >> 1);
            num = reader.get(pivot);

            if (num == target) return pivot;
            if (num > target) right = pivot - 1;
            else left = pivot + 1;
        }

        // there is no target element
        return -1;
    }
}
