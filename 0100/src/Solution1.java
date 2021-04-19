/**
 * @author YonghShan
 * @date 3/18/21 - 21:28
 */
public class Solution1 {
    // Recursion
    /* Runtime: 0ms      O(n)
       Memory: 36.2MB    O(log n), where n is the minimum number of nodes in both tree
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        if (q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
