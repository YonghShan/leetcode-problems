/**
 * @author YonghShan
 * @date 3/14/21 - 15:00
 */
class Solution4 {
    // For BST, the in-order traversal can get an ascending order.
    // We use Integer instead of int as it supports a null value.
    /* Runtime: O(n)
       Memory:  O(n)
     */
    private Integer prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {  // recursion: left
            return false;
        }
        if (prev != null && root.val <= prev) {  // recursion: root
            return false;
        }
        prev = root.val;
        return inorder(root.right);  // recursion: right
    }
}
