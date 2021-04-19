/**
 * @author YonghShan
 * @date 2/16/21 - 15:52
 */

class Solution {
    // Recursion
    /* Runtime: 0ms   O(n)
       Memory: 37MB   O(n) in the worst case
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) && isMirror(t1.left, t2.right) &&  isMirror(t1.right, t2.left);
    }
}
