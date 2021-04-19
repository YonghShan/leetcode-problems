import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 17:11
 */

class Solution1 {
    // Recursion
    /* Runtime: 0ms      O(n)
       Memory: 37.7MB    O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        if (root.left != null) list.addAll(postorderTraversal(root.left));
        if (root.right != null) list.addAll(postorderTraversal(root.right));
        list.add(root.val);

        return list;
    }
}