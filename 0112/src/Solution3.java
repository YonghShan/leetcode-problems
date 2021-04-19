import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 2/16/21 - 23:05
 */

class Solution3 {
    // Recursion: hasPathSum(root, targetSum) == hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val)
    /* Runtime: 0ms    O(n)  每个node只访问一次
       Memory: 39MB    O(log(n)) in best case; O(n) in worst case
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == targetSum) return true;

        int remain = targetSum - root.val;
        return hasPathSum(root.left, remain) || hasPathSum(root.right, remain);
    }
}
