import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/13/21 - 23:47
 */
public class Solution1 {
    // Merger Sort Top-down Recursion
    /* Runtime: 11ms    O(nlogn), where n is the length of nums
                             We recursively divide the input list into two sublists, until a sublist with single element remains.
                             This dividing step computes the midpoint of each of the sublists, which takes O(1) time.
                             This step is repeated n times until a single element remains, therefore the total time complexity is O(n).
                             Then, we repetitively merge the sublists, until one single list remains.
                             There are a total of n elements on each level. Therefore, it takes O(N) time for the merging process to complete on each level.
                             （Divide只是把list拆散，并没有减少元素，所以每层都还是n个元素，只是每个sublist的size不同）
                             And since there are a total of logn levels, the overall complexity of the merge process is O(nlogn)
       Memory: 53.9MB   O(n) since we need to keep the sublists as well as the buffer to hold the merge results at each round of merge process.
     */
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, r);
    }
    private void merge(int[] nums, int l, int r) {
        int mid = l + (r - l) / 2;
        int[] tmp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid || j <= r) {
            if (i > mid || j <= r && nums[i] > nums[j]) {
                tmp[k++] = nums[j++];
            } else {
                tmp[k++] = nums[i++];
            }
        }
        System.arraycopy(tmp, 0, nums, l, r - l + 1);
    }
}