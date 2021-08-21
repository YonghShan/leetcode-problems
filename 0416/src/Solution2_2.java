/**
 * @author YonghShan
 * @date 8/21/21 - 00:07
 */
public class Solution2_2 {
    /* Runtime: 35ms (faster than 62.56%)   O(n * target)
       Memory: 48.8MB (less than 35.51%)    O(target)
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        // 「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) sum += num;
        // 总和为奇数，则必然不能被分成两个「等和子集」
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        boolean[][] f = new boolean[2][target+1];
        // 初始化：先处理「不考虑任何物品」的情况（即dp数组的第一横行的初始值）
        f[0][0] = true;
        // 状态转移：再处理「考虑其他物品」的情况
        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= target; c++) {
                // 不选：
                boolean ns = f[(i-1)&1][c];
                // 选：
                boolean s = c >= nums[i] ? f[(i-1)&1][c-nums[i]] : false;
                f[i&1][c] = ns | s;
            }
        }

        return f[(n-1)&1][target];
    }
}
