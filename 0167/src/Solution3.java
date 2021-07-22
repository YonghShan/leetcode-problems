/**
 * @author YonghShan
 * @date 4/30/21 - 12:06
 */
public class Solution3 {
    // Binary Search
    /* Runtime: 0ms     O(logn) and deteriorates to O(n) when the two numbers are in the center of the input.
       Memory: 39.3MB   O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 这个分析得不好，具体看Notes
            // tmp取numbers[left] + numbers[right]的原因:
            //    因为numbers是sorted，则numbers[left] < numbers[mid] < numbers[right]
            //    所以，numbers[left] + numbers[mid] < numbers[left] + numbers[right] < numbers[mid] + numbers[right]
            //    当numbers[left] + numbers[mid] > target时， 结果一定在[left，mid-1]
            //    当numbers[mid] + numbers[right] < target时， 结果一定在[mid+1，right]
            int tmp = numbers[left] + numbers[right];
            if (tmp == target) {
                return new int[]{left+1, right+1};
            } else if (tmp < target) {
                left = numbers[mid] + numbers[right] < target ? mid+1 : left+1;
            } else {
                right = numbers[left] + numbers[mid] > target ? mid-1 : right-1;
            }
        }

        return new int[]{-1, -1};
    }
}
