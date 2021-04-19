import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 00:05
 */

class Solution1 {
    // Recursion
    /* Runtime: 0ms    O(n)
       Memory: 37MB    O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        list.add(root.val);
        if (root.left != null) list.addAll(preorderTraversal(root.left));
        if (root.right != null) list.addAll(preorderTraversal(root.right));

        return list;
    }
}