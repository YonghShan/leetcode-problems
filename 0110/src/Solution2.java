/**
 * @author YonghShan
 * @date 6/20/21 - 22:53
 */
public class Solution2 {
    // since the height of a tree is always greater than or equal to 0
    // we use -1 as a flag to indicate if the subtree is not balanced
    // Bottom-up Recursion
    /* Runtime: 0ms (faster than 100%)    O(n) 见note
       Memory: 38.9MB (less than 68.21%)  O(n) for the recursion stack
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    // Clever Trick
    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        // left, right subtree is unbalanced or cur tree is unbalanced
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}
