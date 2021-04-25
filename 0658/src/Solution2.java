import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YonghShan
 * @date 4/24/21 - 16:28
 */
public class Solution2 {
    // Modified official solution using Binary Search and two pointers:  修改之后没有那么蠢了
    //     1. find the index of the element, which is equal (when this list has x) or a little bit larger than x (when this list does not have it)
    //     2. set low to its left k-1 position, and high to the right k-1 position of this index as a start.
    //        The desired k numbers must in this rang [index-k, index+k].
    //     3. shrink this range
    /* Runtime: 7ms (faster than 42.42%)    O(logn+k). O(logn) is for the time of binary search, while O(k) is for shrinking the index range to k elements.
       Memory: 40.7MB (less than 61.77%)    O(k) for generating a k length sublist
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> nums = Arrays.stream(arr).boxed().collect(Collectors.toList());  // not sure the TC, probably O(n), sad...
        // TC for the rest part is O(logn+k)
        int n = nums.size();
        if (x <= nums.get(0)) {
            return nums.subList(0, k);
        } else if (nums.get(n - 1) <= x) {
            return nums.subList(n - k, n);
        } else {
            int index = Collections.binarySearch(nums, x); // built-in Binary Search method
            if (index < 0)
                index = -index - 1;
            int low = Math.max(0, index - k), high = Math.min(n - 1, index + k);

            while(high - low + 1 > k) {
                if(arr[high] - x >= x - arr[low])
                    high--;
                else
                    low++;
            }
            return nums.subList(low, high + 1);
        }
    }
}
