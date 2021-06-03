/**
 * @author YonghShan
 * @date 3/14/21 - 12:08
 */
class Solution2 {
    // 和Solution1不一样的思路，依旧recursion实现
    /* Runtime: O(n)
       Memory:  O(n)
     */
    public boolean validate(TreeNode root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }
        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        // The left and right subtree must also be valid.
        return validate(root.right, root.val, high) && validate(root.left, low, root.val);  // 直接将自身的val分别作为其左右子树的最大上限和最小上限
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
}
