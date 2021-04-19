/**
 * @author YonghShan
 * @date 3/26/21 - 19:05
 */
public class Solution1 {
    // Recursion
    /* Runtime: 0ms     O(n)
       Memory: 36.3MB   O(height) = O(n)
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        TreeNode tmp = root.right;
        root.right = invertTree(root.left); // 无论root.left/root.right是否为null，都需要recursion，不然对于像[1,2]会返回结果[1,2,2], 正确的硬是[1,null,2]
        root.left = invertTree(tmp);

        return root;
    }
}
