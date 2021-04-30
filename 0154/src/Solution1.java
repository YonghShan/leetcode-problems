/**
 * @author YonghShan
 * @date 4/29/21 - 11:42
 */
public class Solution1 {
    // 与[0153]不同的是，因为nums中允许duplicates，所以只能将nums[mid]与nums[right]比较
    /* 为什么只能是nums[mid]与nums[right]比较？
       enum all the basic scenarios of this problems：
        case | nums[left] | ? | nums[mid] | ? | nums[right] |   example
          1  |            | > |           | = |             | [4 2 3 3 3]
          2  |            | > |           | < |             | [4 2 3 3 4]
          3  |            | = |           | > |             | [3 3 3 1 2]
          4  |            | = |           | = |             | [3 3 3 2 3]
          5  |            | = |           | < |             | [3 3 3 1 2]
          6  |            | < |           | > |             | [3 4 5 1 2]
          7  |            | < |           | = |             | [1 2 3 3 3]
          8  |            | < |           | < |             | [1 2 3 3 4]
       即：只有nums[left] > nums[mid] > nums[right]这种情况不存在
       如果将nums[left]与nums[mid]相比：
          nums[left] > nums[mid]: 可能为[7 3 4 5 6]或[3 1 2 3 4]，此时min must be landed in [start, mid] inclusively
          nums[left] = nums[mid]: 可能为[3 1 3 3 3]或[2 2 2 1 2]，此时无法确定min
          nums[left] < nums[mid]: 可能为[4 5 6 1 2]或[1 2 3]，此时无法确定min

       如果将nums[mid]与nums[right]相比：
          nums[mid] > nums[right]: 可能为[3 4 5 2 3]，此时min must be in right half
          nums[mid] = nums[right]: 可能为[1 2 3 3 3]或[3 3 3 1 3]，此时无法确定min
          nums[mid] < nums[right]: 可能为[5 1 2 3 4]或[4 5 1 2 3]，此时min must be in left half [left, mid]
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }

        return nums[left];
    }
}
