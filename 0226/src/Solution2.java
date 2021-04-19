import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/26/21 - 19:17
 */
public class Solution2 {
    // BFS
    /* Runtime: 100ms    O(n)
       Memory: 36.6MB    O(n)
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode newRoot = queue.poll();
            TreeNode tmp = newRoot.right;
            newRoot.right = newRoot.left;
            newRoot.left = tmp;
            if (newRoot.left != null) queue.add(newRoot.left);
            if (newRoot.right != null) queue.add(newRoot.right);
        }

        return root;
    }
}
