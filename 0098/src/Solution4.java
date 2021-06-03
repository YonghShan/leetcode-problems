/**
 * @author YonghShan
 * @date 3/14/21 - 15:00
 */
class Solution4 {
    // 推荐！
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
        if (prev != null && root.val <= prev) {  // recursion: root    // prev是当前root在遍历结果中的前一个node的值，如果要保证遍历结果为ascending order，则root.val > prev
            return false;                        // 本题中对于valid BST的判断，不可以包含等于，必须严格大于或严格小于
        }
        prev = root.val;  // prev的update（即prev在什么时候更新）非常重要
        return inorder(root.right);  // recursion: right
    }
}
