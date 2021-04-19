/**
 * @author YonghShan
 * @date 3/11/21 - 16:05
 */
public class Solution2 {
    // Iteration
    /* Runtime: 0ms
       Memory: 39.2MB
     */
    public TreeNode searchBST(TreeNode root, int val) {
//        if (root == null || root.val == val) return root;
//        TreeNode p = root;
//        while (p != null) {
//            if (p.val == val) break;
//            if (p.val > val) {
//                p = p.left;
//            } else {
//                p = p.right;
//            }
//        }
//
//        return p;

        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }
}
