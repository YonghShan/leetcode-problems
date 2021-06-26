/**
 * @author YonghShan
 * @date 6/25/21 - 15:34
 */
public class Solution1 {
    // Recursion: 为了保证是height-balanced BST，选择数组最中间的值作为root，而其左右子树则通过recursion获得
    /* Runtime: 0ms                           O(n) since we visit each node exactly once.
       Memory: 38.8MB (less than 50.12%)      O(N) to keep the output, and O(logN) for the recursion stack.
     */
    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length-1);
    }

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        int rootIdx = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[rootIdx]);
        // root的左子树由[left, rootIdx-1]范围内的数组构成
        root.left = helper(left, rootIdx-1);
        // root的右子树由[rootIdx+1, right]范围内的数组构成
        root.right = helper(rootIdx+1, right);

        return root;
    }
}
