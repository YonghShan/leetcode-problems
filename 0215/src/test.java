import java.util.Random;

/**
 * @author YonghShan
 * @date 3/25/21 - 22:11
 */
public class test {
    public int findKthLargest(int[] nums, int k) {
        quickSelect(nums, 0, nums.length-1, k);
        return nums[nums.length - k];
    }

    public void quickSelect(int[] nums, int l, int r, int k) {
        if (l > r) return ;
        int mid = partition(nums, l, r);
        if (mid > nums.length - k) {
            quickSelect(nums, l, mid-1, k);
        } else if (mid < nums.length - k) {
            quickSelect(nums, mid+1, r, k);
        }
    }

    public int partition(int[] nums, int l, int r) {
        Random random_num = new Random();
        int pivot_index = l + random_num.nextInt(r - l + 1);
        int pivot = nums[pivot_index];
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
