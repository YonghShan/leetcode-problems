/**
 * @author YonghShan
 * @date 1/28/21 - 01:53
 */

class Solution2 {
    // 类似于0027中思路2：Two Pointers的思想
    /* Runtime: 1ms
       Memory: 45M
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) { // nums[i]是现在待判定的值，一旦j遍历到与其不等的值，则说明nums[i]安全，
                nums[i+1] = nums[j]; // 将nums[j]放到nums[i]后
                i++; // 同时，nums[i+1]成为待判定的值
            }
        }
        // 当遍历结束，如果找到与待判定的值不同的值，则循环上一步
        // 一直到遍历结束，都没有找到与待判定值不同的值，说明待判定值是一串重复子串的首位
        // 注意，也要将该判定值作为代表加入返回的无重复子串中
        // 因此，返回的子串的长度应为i+1（实际上是，(i+1) + 1 -> i + 2)
        return i+1;
    }
}
