import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/16/21 - 00:35
 */

class Solution1 {
    // Iteration with Queue (FIFO)
    /* Runtime:
       Memory:
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode currMirror = queue.poll();
            if (curr == null && currMirror == null) continue;
            if (curr == null || currMirror == null) return false;
            if (curr.val != currMirror.val) return false;
            queue.add(curr.left);
            queue.add(currMirror.right);
            queue.add(curr.right);
            queue.add(currMirror.left);
        }
        return true;
    }
}