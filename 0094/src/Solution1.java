import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 16:16
 */

class Solution1 {
    // Recursion
    /* Runtime: 0ms      O(n)
       Memory: 37.5MB    O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        if (root.left != null) list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        if (root.right != null) list.addAll(inorderTraversal(root.right));

        return list;
    }
}
