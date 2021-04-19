/**
 * @author YonghShan
 * @date 3/14/21 - 12:08
 */
class Solution2 {
    // 和Solution1一样的思路，不同的实现
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
        return validate(root.right, root.val, high) && validate(root.left, low, root.val);  // 直接记录最大值和最小值，不用while循环找
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
}
