
/**
 * @author YonghShan
 * @date 3/14/21 - 11:04
 */
public class Solution1 {
    // Recursion: D & C
    /* Runtime: 0ms      每个node都要recursive判断，每次判断还要进行while循环： O(n^2)
       Memory: 38.3MB    O(n)
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left != null) {
            TreeNode l = root.left;
            while (l.right != null) l = l.right;
            if (root.val <= l.val) return false;
        }
        if (root.right != null) {
            TreeNode r = root.right;
            while (r.left != null) r = r.left;
            if (root.val >= r.val) return false;
        }

        return isValidBST(root.left) && isValidBST(root.right);
    }
}
