/**
 * @author YonghShan
 * @date 6/8/21 - 15:23
 */
public class Solution2 {
    // Iteration version
    /* Runtime: 6ms (faster than 14.82%)    O(n) in worst case
       Memory: 47.3 (less than 6.80%)       O(1)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }
}
