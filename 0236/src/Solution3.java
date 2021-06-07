/**
 * @author YonghShan
 * @date 2/20/21 - 16:27
 */
class Solution3 {
    // Recursion "Top-down"：如果root不是p和q的LCA，那就判断root.left或者root.right是不是p和q的LCA
    /* Runtime: 4ms     O(n)
       Memory: 40.6MB
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root.val==p.val || root.val ==q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left!=null && right!=null) return root; // 此时，说明p和q分别处于root的左子树和右子树中
        else if(left!=null) return left; // 在当前root的左子树中发现了p或q
        else return right; // // 在当前root的右子树中发现了p或q （不可能left和right都为null）
    }
}