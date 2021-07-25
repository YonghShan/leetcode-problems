/**
 * @author YonghShan
 * @date 7/23/21 - 12:00
 */
public class Solution3 {
    // Using Cyclic Replacements
    /* Runtime: 2ms (faster than 38.34%)    O(n)
       Memory: 55.9MB (less than 90.22%)    O(1)
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int count = 0;
        // nums.length为奇数：for循环1次，do-while循环nums.length次；
        // nums.length为偶数：for循环a次，do-while循环b次 ==>> ab = nums.length
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current); // 终止条件：回到初始位置
        }
    }
}
