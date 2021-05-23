/**
 * @author YonghShan
 * @date 5/22/21 - 23:18
 */
public class Solution1 {
    // Naive method: 利用DFS（不太好理解）找到所有的分法，再找到minimum largest value for subarrays
    /* Runtime: O(n^m)
       Memory: O(n) for DFS stack
     */
    private int ans;
    private int n, m;
    private void dfs(int[] nums, int i, int cntSubarrays, int curSum, int curMax) {
        System.out.println("i: " + i + " cntSubarrays: " + cntSubarrays + " curSum: " + curSum + " curMax: " + curMax);
        if (i == n && cntSubarrays == m) {
            ans = Math.min(ans, curMax);
            return;
        }
        if (i == n) {
            return;
        }
        if (i > 0) {
            dfs(nums, i + 1, cntSubarrays, curSum + nums[i], Math.max(curMax, curSum + nums[i]));
        }
        if (cntSubarrays < m) {
            dfs(nums, i + 1, cntSubarrays + 1, nums[i], Math.max(curMax, nums[i]));
        }
    }

    public int splitArray(int[] nums, int M) {
        ans = Integer.MAX_VALUE;
        n = nums.length;
        m = M;
        System.out.println("n: " + n + " m:" + m);
        dfs(nums, 0, 0, 0, 0);
        return ans;
    }
}
