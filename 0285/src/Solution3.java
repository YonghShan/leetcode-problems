/**
 * @author YonghShan
 * @date 6/3/21 - 21:09
 */
public class Solution3 {
    // 最佳
    // 利用了BST的特性：左子树小于root，右子树大于root
    // 根据p.val和当前root.val的大小关系，来判断discard哪一半subtree
    /* Runtime: O(logn) in average case and O(n) in worst case
       Memory: O(1)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;  // Now, root is still a potential successor candidate.
                root = root.left;
            }
        }

        return successor;
    }
}
