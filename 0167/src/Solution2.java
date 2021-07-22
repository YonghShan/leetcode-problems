/**
 * @author YonghShan
 * @date 4/30/21 - 11:31
 */
public class Solution2 {
    // 看到"sorted"，不仅仅要考虑Binary Spublic int[] twoSum(int[] numbers, int target) {
    //        int left = 0;
    //        int right = numbers.length-1;
    //        while (left < right) { // Since the problems claimed you may not use the same element twice.
    //            int tmp = numbers[left] + numbers[right];
    //            if (tmp == target) {
    //                return new int[]{left+1, right+1};
    //            } else if (tmp < target) {
    //                left++;
    //            } else {
    //                right--;
    //            }
    //        }
    //
    //        return new int[]{-1, -1};
    //    }earch, 还可以考虑Two Pointers: [0349] [0350]
    /* Runtime: 0ms     O(n)
       Memory: 39.1MB   O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while (left < right) { // Since the problems claimed you may not use the same element twice.
            int tmp = numbers[left] + numbers[right];
            if (tmp == target) {
                return new int[]{left+1, right+1};
            } else if (tmp < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }
}
