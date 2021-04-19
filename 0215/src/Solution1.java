/**
 * @author YonghShan
 * @date 3/25/21 - 21:32
 */
public class Solution1 {
    // pivot取随机没改出来
    // QuickSelect based on QuickSort ([0912] Sort an Array 这里用的是partition1的写法，partition2会慢很多，大概17ms)
    /* Runtime: 7ms (faster than 29.53%)  Quickselect uses the same overall approach as quicksort,
                                          choosing one element as a pivot and partitioning the data in two based on the pivot,
                                          accordingly as less than or greater than the pivot.
                                          However, instead of recursing into both sides, as in quicksort,
                                          quickselect only recurses into one side – the side with the element it is searching for.
                                          This reduces the average complexity from O(n log n) to O(n), with a worst case of O(n^2).
       Memory: 39.7MB (less than 26.33%)  O(1) ?会有Recursion call stack啊
     */
    public int findKthLargest(int[] nums, int k) {
        quickSelect(nums, 0, nums.length-1, k);
        return nums[nums.length - k];
    }

    public void quickSelect(int[] nums, int l, int r, int k) {
        if (l > r) return ;
        int mid = partition(nums, l, r);
        if (mid > nums.length - k) {
            quickSelect(nums, l, mid-1, k); // 这里mid减不减1，不影响
        } else if (mid < nums.length - k) {
            quickSelect(nums, mid+1, r, k);
        }
    }

    public int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }
}
