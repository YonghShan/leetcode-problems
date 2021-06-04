import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 6/3/21 - 17:29
 */
public class Solution2Recursion {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        ArrayList<Integer> res = inorderTraversal(root);
        int idx = res.indexOf(p.val);    // O(n)
        return idx == res.size() - 1 ? null : new TreeNode(res.get(idx + 1)); // O(1)
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {    // O(n) & O(n)
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;

        if (root.left != null) list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        if (root.right != null) list.addAll(inorderTraversal(root.right));

        return list;
    }
}
