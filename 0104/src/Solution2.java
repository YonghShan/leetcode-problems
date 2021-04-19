/**
 * @author YonghShan
 * @date 2/15/21 - 23:07
 */

class Solution2 {
    // Recursion "Bottom-up"
    /* Runtime: 0ms     O(n)
       Memory: 38.9MB   O(log(n))  除非tree是完全不平衡的，全都只有左/右孩子，此时为O(n)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int maxleft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxleft, maxRight) + 1;
    }
}