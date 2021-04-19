/**
 * @author YonghShan
 * @date 4/5/21 - 19:26
 */
public class Solution2 {
    // Iteration
    /* Runtime: 0ms    O(logN) in the average case and O(N) in the worst case
       Memory: 39.4MB (less than 90.64%)   O(1)
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode p = root;
        while (p != null) {
            if (val < p.val) {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    return root;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    p.right = new TreeNode(val);
                    return root;
                } else {
                    p = p.right;
                }
            }
        }

        return new TreeNode(val);
    }
}
