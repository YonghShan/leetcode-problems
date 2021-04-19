
/**
 * @author YonghShan
 * @date 3/11/21 - 16:02
 */
public class Solution1 {
    // Recursion
    /* Runtime: 0ms     O(H), where H is a tree height. That results in O(logN) in the average case, and O(N) in the worst case.
       Memory: 39.8MB   same as Time Complexity
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return root.val > val? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
