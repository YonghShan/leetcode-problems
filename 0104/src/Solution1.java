/**
 * @author YonghShan
 * @date 2/15/21 - 23:01
 */

class Solution1 {
    // Recursion "Top-down"
    /* Runtime: 0ms     O(n)
       Memory: 39MB     O(log(n))  除非tree是完全不平衡的，全都只有左/右孩子，此时为O(n)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return helper(root, 1);
    }

    public int helper(TreeNode node, int depth) {
        if (node == null) return depth-1; // 此时，node为null，depth多加了1
        int maxLeft  = helper(node.left, depth+1);
        int maxRight = helper(node.right, depth+1);

        return Math.max(maxLeft, maxRight);
    }
}