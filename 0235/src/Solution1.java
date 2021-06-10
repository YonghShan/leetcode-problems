
/**
 * @author YonghShan
 * @date 6/8/21 - 15:07
 */
public class Solution1 {
    // Recursion: [0236] Solution 3，但又利用了BST's property
    /* Runtime: 11ms (faster than 6.14%)  O(n) in worst case
       Memory: 46.6MB (less than 12.51%)  O(n) for stack in worst case
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
