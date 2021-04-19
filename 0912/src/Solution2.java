
/**
 * @author YonghShan
 * @date 3/13/21 - 22:56
 */
public class Solution2 {
    // Merge Sort Bottom-up Iteration
    /* Runtime: 13ms
       Memory: 53.4MB
     */
    public int[] sortArray(int[] nums) {
        mergeSort2(nums);
        return nums;
    }
    private void mergeSort2(int[] nums) {
        for (int size = 1; size < nums.length; size *= 2) { // 每个待merge对象的length
            for (int i = 0; i < nums.length - size; i += 2 * size) { // 每个待merge对象的startIndex：指向第一个待merge对象的头部
                int mid = i + size - 1; // 指向第一个待merge对象的尾部：这里保存mid是为了方便找到第二个待merge对象的头部
                int end = Math.min(i + 2 * size - 1, nums.length - 1); // 指向第二个待merge对象的尾部
                merge2(nums, i, mid, end);
            }
        }
    }
    private void merge2(int[] nums, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0; // i指向第一个待merge对象的头部，j指向第二个待merge对象的头部：merge都是从两个待merge对象的头部开始向后遍历的
        while (i <= mid || j <= r) {
            if (i > mid || j <= r && nums[i] > nums[j]) { // i>mid说明第一个待merge对象的值已经被排完了，剩的只有第二个待merge对象的元素
                tmp[k++] = nums[j++];
            } else { // 此时，指向的第一个待merge对象中的值大于指向的第二个待merge对象的值
                tmp[k++] = nums[i++];
            }
        }
        // 用tmp修改nums的部分内容
        System.arraycopy(tmp, 0, nums, l, r - l + 1); // tmp: 原数组；0: 原数组的起始索引；nums: 目标数组 ；l: 目标数组被修改的起始索引；r-l+1: 要复制的长度
    }
}
